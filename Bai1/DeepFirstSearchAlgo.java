package Lap2.Bai1;



import java.util.*;

public class DeepFirstSearchAlgo implements ISearchAlgo {

    @Override
    public Node execute(Node root, String goal) {
        Stack<Node> frontier = new Stack<Node>();
        List<Node> explored = new ArrayList<>();
        frontier.push(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.pop();
            explored.add(current);
            if (current.getLabel().equals(goal)) return current;
            for (Node child : current.getChildrenNodes()) {
                if (frontier.contains(child) || explored.contains(child)) continue;
                frontier.push(child);
                child.setParent(current);
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
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
        ISearchAlgo algo1 = new DeepFirstSearchAlgo();
        Node result = algo1.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));
    }
}