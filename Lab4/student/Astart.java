package lab4.inform.student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Astart implements IInformedSearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier  = new PriorityQueue<>(new NodeComparator_New());
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
                        n.setG(current.getG()+ child.getWeight());
                        frontier.add(n);
                        n.setParent(current);

                    } else if(frontier.contains(n) && n.getH() > (current.getH() + child.getWeight()) ) {
                        frontier.remove(n);
                        n.setG(current.getH() + child.getWeight());
                        n.setParent(current);
                        frontier.add(n);
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