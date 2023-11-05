package lab4.inform.student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Greedy implements IInformedSearchAlgo{

    @Override
    public Node execute(Node root, String goal)  {
        PriorityQueue<Node> frontier  = new PriorityQueue<>(new NodeComparator());
        frontier.add(root);
        List<Node> explored = new ArrayList<>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)) {
                return current;
            } else {
                explored.add(current);
                List<Edge> childEdges = current.getChildren();
                for (Edge child : childEdges) {
                    Node n = child.getEnd();
                    if(!explored.contains(n) || frontier.contains(n)) {
                        frontier.add(n);
                        n.setParent(current);

                    } else if(frontier.contains(n) && n.getH() > (current.getH()) ) {

                        n.setParent(current);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        return null;
    }
}