import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class Dijkstra {

    // Finds shortest path from Node src on given Graph g
    public static Graph calcShortestPath(Graph g, Node src) {

        src.setCost(0);

        //TreeSet data structures for nodes we need to visit and add them once visited
        TreeSet<Node> visitedNodes = new TreeSet<>();
        TreeSet<Node> unVisitedNodes = new TreeSet<>();

        //Add root node to visited and everything else to unvisited
        visitedNodes.add(src);
        unVisitedNodes.addAll(g.getNodes());

        //While we still have unvisited nodes
        while (!unVisitedNodes.isEmpty()) {
            Node openNode = unVisitedNodes.first();

            unVisitedNodes.remove(openNode);

            //Begin building neighborlist and get path cost
            for (Map.Entry<Node, Integer> n :
                    openNode.getNeighborsList().entrySet()) {
                Node adjNode = n.getKey();
                Integer edgeCost = n.getValue();

                //Compare path costs to get shortest path
                if (!visitedNodes.contains(adjNode)) {
                    if (openNode.getCost() + edgeCost < adjNode.getCost()) {
                        adjNode.setCost(openNode.getCost() + edgeCost);
                        LinkedList<Node> shortestPath = new LinkedList<>(openNode.getPath());
                        shortestPath.add(openNode);
                        adjNode.setShortestPath(shortestPath);
                    }
                    unVisitedNodes.add(adjNode);
                }
            }
            visitedNodes.add(openNode);
        }
        return g;
    }

    public static Graph calcCheapestWineLine(Graph g, Node src) {

        src.setCost(0);

        //TreeSet data structures for nodes we need to visit and add them once visited
        TreeSet<Node> visitedNodes = new TreeSet<>();
        TreeSet<Node> unVisitedNodes = new TreeSet<>();

        //Root nodes
        visitedNodes.add(src);
        unVisitedNodes.add(src);

        //While we still have unvisited nodes
        while (!unVisitedNodes.isEmpty()) {

            Node openNode = unVisitedNodes.first();
            unVisitedNodes.remove(openNode);

            //Get node keys and lengths of wine lines
            for (Map.Entry<Node, Integer> n :
                    openNode.getNeighborsList().entrySet()) {
                Node adjNode = n.getKey();
                Integer lineLength = n.getValue();

                //Calculate path costs based off line lengths 
                if (!visitedNodes.contains(adjNode)) {
                    if (openNode.getCost() + lineLength < adjNode.getCost()) {
                        adjNode.setCost(openNode.getCost() + lineLength);
                        LinkedList<Node> shortestPath = new LinkedList<>(openNode.getPath());
                        shortestPath.add(openNode);
                        adjNode.setShortestPath(shortestPath);
                    }
                    unVisitedNodes.add(adjNode);
                }
            }
            visitedNodes.add(openNode);
        }
        return g;
    }


}
