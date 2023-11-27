package game_alphabeta_student;

public class Test {
    public static void main(String[] args) {
        // Create a sample tree
        Node root = new Node("A");

        Node b = new Node("B");
        Node f = new Node("F");
        Node g = new Node("G", -5);
        Node n = new Node("N", 4);
        Node o = new Node("O");
        Node w = new Node("W", -3);
        Node x = new Node("X", -5);


        Node c = new Node("C");
        Node h = new Node("H", 3);
        Node i = new Node("I", 8);
        Node j = new Node("J");
        Node p = new Node("P", 9);
        Node q = new Node("Q", -6);
        Node r = new Node("R", 0);

        Node d = new Node("D", 0);

        Node e = new Node("E");
        Node k = new Node("K");
        Node l = new Node("L", 2);
        Node m = new Node("M");
        Node s = new Node("S", 3);
        Node t = new Node("T", 5);
        Node u = new Node("U", -7);
        Node v = new Node("V", -9);


        root.addChild(b);
        root.addChild(c);
        root.addChild(d);
        root.addChild(e);

        b.addChild(f);
        b.addChild(g);
        f.addChild(n);
        f.addChild(o);
        o.addChild(w);
        o.addChild(x);

        c.addChild(h);
        c.addChild(i);
        c.addChild(j);
        j.addChild(p);
        j.addChild(q);
        j.addChild(r);

        e.addChild(k);
        e.addChild(l);
        e.addChild(m);
        k.addChild(s);
        k.addChild(t);
        m.addChild(u);
        m.addChild(v);


        // Create the search algorithm
        ISearchAlgo miniMax = new MiniMaxSearchAlgo();
        ISearchAlgo alphaBeta = new AlphaBetaSearchAlgo();

        // Execute the algorithm on the root node
        System.out.println("AlphaBeta: ");
        alphaBeta.execute(root);


        System.out.println();
        System.out.println("Minimax: ");
        miniMax.execute(root);

    }
}
