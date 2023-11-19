package Lab7;

import java.util.*;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<Node>();
    Random rd = new Random();

    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    public Node execute() {
// Enter your code here
        initPopulation();
        int k =0;

        while (k<MAX_ITERATIONS) {
            List<Node> list = new ArrayList<>();
            for(int i=0; i<POP_SIZE;i++) {
                Node x = getParentByRandomSelection();
                Node y = getParentByRandomSelection();
                Node child =reproduce(x,y);
                if(rd.nextDouble()<MUTATION_RATE) {
                    mutate(child);
                }
                if(child.getH() ==0) {
                    return child;
                } else {
                    list.add(child);
                }
                population = list;
            }

            k++;
        }
        Collections.sort(population, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getH() - o2.getH();
            }
        });
        return population.get(0);
    }

    // Mutate an individual by selecting a random Queen and
//move it to a random row.
    public void mutate(Node node) {
// Enter your code here
        int i = rd.nextInt(Node.N);
        int row = rd.nextInt(Node.N);
        node.setRow(i,row);

    }

    public Node reproduce(Node x, Node y) {
// Enter your code here
        Node child = new Node();
        child.generateBoard();
        int c = rd.nextInt(Node.N);
        for(int i=0; i<c;i++) {
            child.setRow(i,x.getRow(i));

        }
        for(int i =c+1;i<Node.N;i++) {
            child.setRow(i,y.getRow(i));
        }
        return child;
    }

    // Select K individuals from the population at random and
//select the best out of these to become a parent.
    public Node getParentByTournamentSelection(int k) {
// Enter your code here
        List<Node> list = new ArrayList<>();
        for(int i =0; i<k; i++ ) {
            list.add(population.get(rd.nextInt(POP_SIZE)));
        }
        int temp =Integer.MAX_VALUE;
        Node result = null;
        for(Node n: list) {
            if(n.getH() < temp) {
                temp=n.getH();
                result=n;
            }
        }

        return result;
    }

    //Select a random parent from the population
    public Node getParentByRandomSelection() {
        Node n = population.get(rd.nextInt(100));

// Enter your code here
        return n;
    }


}
