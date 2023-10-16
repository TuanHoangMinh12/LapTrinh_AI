package Lap2.Bai4;


import java.util.*;

public class UniformCostSearchAlgo implements ISearchAlgo {

    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier =
                new PriorityQueue<Node>((node1, node2) -> {
                    int compare = Double.compare(node1.getPathCost(), node2.getPathCost());
                    return (compare == 0) ? node1.getLabel().compareTo(node2.getLabel()) : compare;
                });
        List<Node> explored = new ArrayList<>();
//        starting
        frontier.offer(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)) return current;
            explored.add(current);
            for (Edge edge : current.getChildren()) {
                Node child = edge.getEnd();
//                current path cost from root to this node
                double currentCost = current.getPathCost() + edge.getWeight();
                double previousCost = child.getPathCost();
                if (explored.contains(child)) continue;
                else {
                    if (!frontier.contains(child))  {
                        child.setPathCost(currentCost);
                        child.setParent(current);
                        frontier.add(child);
                        continue;
                    }
//                    Do we need update the path cost?
                    currentCost = Math.min(previousCost, currentCost);
                    Node updateParent = (previousCost > currentCost) ? current : child.getParent();
                    child.setParent(updateParent);
                    child.setPathCost(currentCost);
                }
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
        ISearchAlgo algo1 = new UniformCostSearchAlgo();
        Node result = algo1.execute(nodeS, "G");
        System.out.println(NodeUtils.printPath(result));
        System.out.println("Cost: "+result.getPathCost());
    }
}