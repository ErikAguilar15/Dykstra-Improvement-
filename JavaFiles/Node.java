import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable {

    private String nodeID;
    private String srcPortID;
    private String dstPortID;
    private Float distanceFromSrc = Float.MAX_VALUE;
    private Float pipeLength = Float.MAX_VALUE;

    private LinkedList<Node> shortestPath = new LinkedList<>();

    // Constructor with Max Value
    public Node(String n) {
        this.nodeID = n;
    }

    // Constructor with given distanceFromSrc
    public Node(String n, Float c) {
        this.nodeID = n;
        this.pipeLength = c;
    }

    public Node(String src, String dst, Float pipeLength) {
        this.nodeID = src + "->" + dst;
        this.srcPortID = src;
        this.dstPortID = dst;
        this.pipeLength = pipeLength;
    }

    public Node(String pipeID, String src, String dst, Float pipeLength) {
        this.nodeID = pipeID;
        this.srcPortID = src;
        this.dstPortID = dst;
        this.pipeLength = pipeLength;
    }

    public String getName() {
        return nodeID;
    }

    public void setName(String s) {
        this.nodeID = s;
    }

    Float getCost() {
        return distanceFromSrc;
    }

    void setCost(Float c) {
        this.distanceFromSrc = c;
    }

    Float getPipeLength() {
        return this.pipeLength;
    }

//    public Map<Node, Integer> getNeighbors() {
//        return neighbors;
//    }
//
//    public void setNeighbors(Map<Node, Integer> aN) {
//        this.neighbors = aN;
//    }
//
//    public Map<Node, Integer> getNeighborsList() {
//        List<Map.Entry<Node, Integer>> list =
//                new LinkedList<>(neighbors.entrySet());
//
//        // Sort the list
//        Collections.sort(list, Comparator.comparing(o -> (o.getValue())));
//
//        HashMap<Node, Integer> temp = new LinkedHashMap<>();
//        for (Map.Entry<Node, Integer> n : list) {
//            temp.put(n.getKey(), n.getValue());
//        }
//        return temp;
//    }

//    public void addAdjacentNode(Node n, Integer c) {
//        neighbors.put(n, c);
//    }

//    public void addPossibleConnection(Graph g, String graphNode, Integer c) {
//        Node n = g.getNode(graphNode);
//        if (n == null) {
//            System.out.println("Can't add possible connection, Node is null");
//        } else {
//            n.setCost(c + n.getCost());
//            edges.add(n);
////            edges.add(n, g.pipes.get(n.nodeID).length);
//        }
//
//    }


    List<Node> getPath() {
        return this.shortestPath;
    }

//    public void printPath() {
//        System.out.print("Shortest Path: " + shortestPath + "-> " + this);
//    }

    void setShortestPath(LinkedList<Node> l) {
        this.shortestPath = l;
    }

    void clearRoute() {
        this.shortestPath.clear();
    }

    int pipesInRoute() {
        return shortestPath.size();
    }

    Node getRoute(Integer index) {
        return shortestPath.get(index);
    }

    @Override
    public int compareTo(Object o) {
        if (this.distanceFromSrc.equals(((Node) o).distanceFromSrc)) {
            return 0;
        } else if (this.distanceFromSrc > ((Node) o).distanceFromSrc) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        if (this.srcPortID != null && this.dstPortID != null)
            return (this.srcPortID + "-" + this.dstPortID + " " + this.distanceFromSrc);
        else
            return (this.nodeID + " " + this.distanceFromSrc);
    }

    void printLine() {
        for (Node n : shortestPath) {
            System.out.print("(" + n + ") -> ");
        }
        System.out.print("(" + this + ")\n");
//        System.out.print(shortestPath + "-> " + this + "\n");
    }

}