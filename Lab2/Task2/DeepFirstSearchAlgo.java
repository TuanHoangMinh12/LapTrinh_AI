package Lab2.Task2;


import java.util.*;

public class DeepFirstSearchAlgo implements ISearchAlgo {

    @Override
    public Node execute(Node root, String goal) {
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        boolean check = false;
        Queue<Node> frontier = new LinkedList<Node>();
        List<Node> explored = new ArrayList<>();
        frontier.offer(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.add(current);
            // meet the start point
            if (current.getLabel().equals(goal) && check) return current;
            if (current.getLabel().equals(start)) {
                check = true;
                frontier.clear();
                explored.clear();
                current.setParent(null);
            }
            for (Node child : current.getChildrenNodes()) {
                if (frontier.contains(child) || explored.contains(child)) continue;
                frontier.offer(child);
                child.setParent(current);
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
        ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
        Node result = algo1.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));
    }
}