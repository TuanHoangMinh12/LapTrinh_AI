package Lab2.Task5;



import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {

    @Override
    public Node execute(Node root, String goal) {
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier =
                new PriorityQueue<Node>((node1, node2) -> {
                    int compare = Double.compare(node2.getPathCost(), node1.getPathCost());
                    return (compare == 0) ? node1.getLabel().compareTo(node2.getLabel()) : compare;
                });
        List<Node> explored = new ArrayList<>();
        frontier.offer(root);
        boolean check = false;
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.add(current);
            if (current.getLabel().equals(start)) {
                check = true;
                for (Node e : explored) e.setPathCost(0);
                for (Node e : frontier) e.setPathCost(0);
                explored.clear();
                frontier.clear();
                current.setParent(null);
//                System.out.println("checked");
            }
            if (current.getLabel().equals(goal) && check) return current;
            for (Edge edge : current.getChildren()) {
                Node child = edge.getEnd();
//                current path cost from root to this node
                double currentCost = current.getPathCost() + edge.getWeight();
                double previousCost = child.getPathCost();
//                System.out.printf("\n%s current: %f, previous: %f", child, currentCost, previousCost);
                if (explored.contains(child)) continue;
                else {
                    if (!frontier.contains(child))  {
                        child.setPathCost(currentCost);
                        child.setParent(current);
                        frontier.add(child);
                        continue;
                    }
//                    Only update cost if already met the start Point
                    currentCost = Math.min(previousCost, currentCost);
                    Node updateParent = (previousCost > currentCost) ? current : child.getParent();
                    child.setParent(updateParent);
                    child.setPathCost(currentCost);
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
        ISearchAlgo algo1 = new UniformCostSearchAlgo();
        Node result = algo1.execute(nodeS, "A", "G");
        System.out.println(NodeUtils.printPath(result));
        System.out.println("Cost: "+result.getPathCost());
    }
}