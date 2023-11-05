package Lab2.Task6;


import java.util.*;

public class DepthLimitSearch  {
    public Node execute(Node root, String goal, int limitedDepth) {
        Stack<Node> frointer = new Stack<>();
        ArrayList<Node> explored = new ArrayList<>();
        frointer.add(root) ;

        while (!frointer.isEmpty()) {
            Node current = frointer.pop();
            if(current.getLabel().equals(goal)) {
                return current;
            } else {
                if(current.getDepth() <limitedDepth) {
                    if(true) {
                        Collections.sort(frointer, new Comparator<Node>() {
                            @Override
                            public int compare(Node o1, Node o2) {
                                return o2.getLabel().compareTo(o1.getLabel());
                            }
                        });
                    }
                    List<Node> children = current.getChildrenNodes();
                    for(Node child : children) {
                        if(!frointer.contains(child)&& !explored.contains(child)) {
                            frointer.add(child);
                            child.setDepth(current.getDepth()+1);

                        }
                    }
                }
            }

        }
        return null;
    }

    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A"); Node nodeB = new Node("B");
        Node nodeC = new Node("C"); Node nodeD = new Node("D");
        Node nodeE = new Node("E"); Node nodeF = new Node("F");
        Node nodeG = new Node("G"); Node nodeH = new Node("H");
        nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);


        DepthLimitSearch depthFirstSearchAlgo = new DepthLimitSearch();
        System.out.println(NodeUtils.printPath(depthFirstSearchAlgo.execute(nodeS,"G",3)));


    }
}