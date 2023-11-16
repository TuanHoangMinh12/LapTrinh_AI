package student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node {
    public static final int N = 8;
    private Queen[] state;

    public Node() {
        // generateBoard();
        state = new Queen[N];
    }

    public Node(Queen[] state) {
        this.state = new Queen[N];
        for (int i = 0; i < state.length; i++) {
            this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
        }
    }

    public Node(Node n) {
        this.state = new Queen[N];
        for (int i = 0; i < N; i++) {
            Queen qi = n.state[i];
            this.state[i] = new Queen(qi.getRow(), qi.getColumn());
        }
    }

    public void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            state[i] = new Queen(random.nextInt(N), i);
        }
    }

    public int getH() {
        int heuristic = 0;
        // Enter your code here
        for(int i=0 ; i<N-1;i++) {
            for(int j=1; j<N;j++) {
                Queen queen1 = this.state[i];
                Queen queen2 = this.state[j];
                if(queen1.isConflict(queen2)) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    public List<Node> generateAllCandidates() {
        List<Node> result = new ArrayList<Node>();
        for(int i=0; i<N; i++) {
            Node n = new Node(this.state);
            n.state[i].move();
            result.add(n);

        }
        // Enter your code here
        return result;
    }

    public Node selectNextRandomCandidate() {
        // Enter your code here
        List<Node> list = generateAllCandidates();
        int n = new Random().nextInt(8);
        Node node = list.get(n);
        return node;
    }
    public Node getBestCandidates() {
        List<Node> list = generateAllCandidates();
        Node result = null;
        int temp=Integer.MAX_VALUE;
        for(Node node :list) {
            if(node.getH() < temp) {
                temp = node.getH();
                result =node;
            }
        }
        return result;
    }
    public Node execute(Node initialState) {
        Node current = initialState;
        Node neighbor = null;
        while (true) {
            neighbor = current.getBestCandidates();
            if(neighbor.getH() <current.getH()) {
                current=neighbor;
            }
            return current;
        }

    }
    public Node  SA_algorithm(Node intialState){
        Node current = intialState;
        Node next = null;
        int t = 1000;
        while (current.getH() != 0) {
            if(t == 0) {
                return current;
            }
            next = current.selectNextRandomCandidate();
            double deltaE = next.getH() - current.getH();
            if(deltaE < 0) {
                current = next;
            } else {
                if(Math.exp(deltaE / t) > Math.random()) {
                    current = next;
                }
            }
            t--;
        }
        return null;
    }
    public void displayBoard() {
        int[][] board = new int[N][N];
        // set queen position on the board
        for (int i = 0; i < N; i++) {
            board[state[i].getRow()][state[i].getColumn()] = 1;
        }
        // print board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q" + " ");
                } else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }
}