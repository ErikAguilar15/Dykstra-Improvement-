import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Dijkstra {

    // Finds shortest path from Node src on given Graph g
//    public static Graph runBasicDijkstra(Graph g, Node src) {
//
//        src.setDistCost(0);
//
//        //TreeSet data structures for nodes we need to visit and add them once visited
//        TreeSet<Node> visitedNodes = new TreeSet<>();
//        TreeSet<Node> unVisitedNodes = new TreeSet<>();
//
//        //Add root node to visited and everything else to unvisited
//        visitedNodes.add(src);
//        unVisitedNodes.addAll(g.getNodes());
//
//        //While we still have unvisited nodes
//        while (!unVisitedNodes.isEmpty()) {
//            Node openNode = unVisitedNodes.first();
//
//            unVisitedNodes.remove(openNode);
//
//            //Begin building neighborlist and get path cost
//            for (Map.Entry<Node, Integer> n :
//                    openNode.getNeighborsList().entrySet()) {
//                Node adjNode = n.getKey();
//                Integer edgeCost = n.getValue();
//
//                //Compare path costs to get shortest path
//                if (!visitedNodes.contains(adjNode)) {
//                    findShortcut(openNode, adjNode, edgeCost);
//                    unVisitedNodes.add(adjNode);
//                }
//            }
//            visitedNodes.add(openNode);
//        }
//        return g;
//    }

    static void findMinPaths(Graph g, Node startPort) {
        findMinPaths( g, startPort, true );
    }

    static void findMinPaths(Graph g, Node startPort, Boolean considerInUse) {

        startPort.setDistCost( (float) 0.0 );

        //TreeSet data structures for nodes we need to visit and add them once visited
        TreeSet<Node> expanded = new TreeSet<>();
        TreeSet<Node> frontier = new TreeSet<>();
        //TODO:: Have list of clipped edges, if clipped is >0 then check if hose connections does not include destination, else connect to a clip.
        // Path Start nodes
        expanded.add( startPort );
        frontier.add(startPort);

        //While we still have unvisited nodes
        while (!frontier.isEmpty()) {

            Node currentNode = frontier.first();
            frontier.remove(currentNode);

            //Get node keys and lengths of wine lines
            for (Edge edge : g.findNeighbors( currentNode.getID() )) {
                if (considerInUse || (!considerInUse && !edge.checkInUse())) {
                    //Calculate path costs based off line lengths

                    Node adjPipe = edge.getNeighbor( currentNode );
                    Float lineLength = edge.getCost();

                    if (!expanded.contains( adjPipe )) {
                        findShortcut( currentNode, adjPipe, lineLength );
                    }
                    if (!expanded.contains( adjPipe ) && currentNode != adjPipe)
                        frontier.add( adjPipe );
                }
            }
            expanded.add(currentNode);
        }
//        return g;
    }

    private static void findShortcut(Node currentNode, Node adjPipe, Float lineLength) {
        if (currentNode.getDistCost() + lineLength < adjPipe.getDistCost()) {
            adjPipe.setDistCost( currentNode.getDistCost() + lineLength );
            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getPath());
            shortestPath.add(currentNode);
            adjPipe.setShortestPath(shortestPath);
        }
    }

    static void resetCosts(Graph g) {
        for (Node n :
                g.getPipes()) {
            n.setDistCost( Float.MAX_VALUE );
            n.clearRoute();
        }
    }

    static void findPaths(Graph g, int n, Float srcTank, Float destTank) {
        int cons; //# of connections
        Node p1;
        while (n > 0) { //# of paths
            findMinPaths( g, g.getPipe( srcTank ) ); //runs dijkstra
//            g.printDistanceTree();

            cons = g.getPipe( destTank ).pipesInRoute(); //return # of connections
            if (cons < 1) //short path maybe 2
                break;


            System.out.println( g.connections.size() );
            if (cons > 6){
                removeExpensiveEdge(g, g.getPipe(destTank).getPath());
//            	System.out.println( g.connections.size());
            } else {
                System.out.println( "First Shortest Path Found :" + g.getPipe( destTank ).getPath() + ", " + g.getPipe( destTank ) );
            }


//            p1 = g.getPipe( destTank ).getRoute( cons - 1 ); //returns node just before tank
//            g.dropConnection( p1.getID(), destTank.toString() ); //node just before and tank
            resetCosts( g );
            n--;
        }
    }


    static void findAltPaths(Graph g, int n, Float srcTank, Float destTank) {
        int cons; //# of connections
        List<List<Node>> paths = new ArrayList<>();
        Node p1;
        while (n > 0) { //# of paths
            findMinPaths( g, g.getPipe( srcTank ) ); //runs dijkstra
            cons = g.getPipe( destTank ).pipesInRoute(); //return # of connections
            if (cons < 1) {//short path maybe 2
                //TODO:: Fix so we only consider new paths, this does a decent job at finding new ones, we can print all paths once we find them that way they can be sorted by their cost rather than by how they were found.
//                g.restoreRandomConnection();
//                findMinPaths( g, g.getPipe( srcTank ) );
//                cons = g.getPipe( destTank ).pipesInRoute();
                break;
            }
            if (!containsPath( paths, g.getPipe( destTank ).getPath() )) {
                paths.add( g.getPipe( destTank ).getPath() );
                g.getPipe( destTank ).printLine();
            }

//            System.out.println( "First Shortest Path Found :" + g.getPipe( destTank ).getPath() + ", "+ g.getPipe( destTank ));
//            System.out.println( g.connections.size() );
            removeExpensiveEdge( g, g.getPipe( destTank ).getPath() );
            resetCosts( g );
            n--;
        }
    }

    private static boolean containsPath(List<List<Node>> paths, List<Node> path) {


        if (paths.size() == 0) return false;
        for (List<Node> p :
                paths) {
            if (sameLineups( p, path )) return true;
        }
//        System.out.println( "containsPath() defaulted to returning false" );
        return false;
    }


    static boolean sameLineups(List<Node> path1, List<Node> path2) {
        /*
         * Last node distance different or list diff lengths the diff path
         * Same cost then analyse path itself
         */
        if (path1.size() == 0 && path2.size() == 0) return true;
        if (path1.size() == 0 && path2.size() > 0) return false;
        if (path1.size() != 0 && path2.size() != 0)
            if (path1.get( path1.size() - 1 ).getDistCost() == path2.get( path2.size() - 1 ).getDistCost())
                return true;
        for (int i = 0; i < path1.size(); i++) if (path1.get( i ).getID() == path2.get( i ).getID()) return true;

        return true;
    }


    static void removeExpensiveEdge(Graph g, List<Node> path) {
        int i = 0;
        int maxnode = 0;
        float maxcost = 0;
        float currentcost, nextcost;
        Node currentnode = new Node();
        Node nextnode = new Node();
        Edge maxedge = new Edge();

        // Could change to 1 since first is always a tank
        for (i = 0; i < path.size()-1; i++) {
//    			Node n = new Node();
            Node n = path.get(i);
            // need to change to differentiate between tank and pipe, temporary
            if (n.getPortIn() != null && n.getPortOut() != null)  {
                nextnode = path.get(i+1); //get node next in path

                currentcost = n.getWeight();
                nextcost = nextnode.getWeight();

                float currentAndNext = currentcost + nextcost;
                if (currentAndNext > maxcost) {
                    maxcost = currentAndNext;
                    maxnode = i;

                }

            }
        }

        currentnode = path.get(maxnode);
        nextnode = path.get(maxnode+1);
        maxedge = g.getEdge(currentnode, nextnode);
        if (maxedge != null) {
//    		    System.out.println( maxedge );
            g.dropConnection(maxedge);
        }

    }

    static void tooManyConnections(Graph g, int n, Float srcTank, Float destTank){
        int cons; //Number of connections

        findMinPaths( g, g.getPipe( srcTank ) );    //Run dijkstra algorithm
        cons = g.getPipe( destTank ).pipesInRoute();    //Get number of connections

        do {
            // removeExpensiveEdge(g, g.getPipes(destTank).getPath());
            findMinPaths(g, g.getPipe(srcTank));
        } while (cons > 6);

    }

    static void clipsOverHoses(Graph g, List<Node> path){
        /*
        Check if hoses are used
            If yes, begin from this node
            find different paths without hose
            I think I solved this problem!

         */

    }


    static void mergePaths(Graph g, Float srcTank, List<Node> path, Node dest) {

        List<Node> oldPath = new LinkedList<>();
        List<Node> newPath = new LinkedList<>();

        // Flag marks the point in which we join paths
        boolean seen = false;
        Node current;
        Node closest = null;
        Float min = Float.MAX_VALUE;

        // Deep Copy of path since we reset their costs
        for (Node n : path) {
            oldPath.add( new Node( n ) );
        }
        oldPath.add( new Node( dest ) );

        resetCosts( g );
        findMinPaths( g, g.getPipe( srcTank ) ); //

//        g.printDistanceTree();

        for (int i = 0; i < oldPath.size(); i++) {
            current = g.getPipe( oldPath.get( i ).getID() );
            if (min > current.getDistCost()) {
                closest = current;
                min = current.getDistCost();
            }

        }
        if (closest != null) {
            newPath = closest.getPath();
            newPath.add( closest );

            for (Node n : oldPath) {
                if (!seen) {
                    if (n.getID().equals( closest.getID() ))
                        seen = true;
                } else
                    newPath.add( g.getPipe( n.getID() ) );

            }
//        System.out.println( "Old Path: \t\t\t\t" + oldPath);

//            System.out.println( "Connecting to old Path \t" );
            for (int i = 0; i < newPath.size() - 1; i++) {
                System.out.print( "(" + newPath.get( i ) + ") -> " );
            }
            System.out.print( "(" + newPath.get( newPath.size() - 1 ) + ")" );
        } else {
            System.out.println( "Unable to merge Paths" );
        }
    }


}