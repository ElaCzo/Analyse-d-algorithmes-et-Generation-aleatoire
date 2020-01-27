package main.java.Remy;

import java.util.ArrayList;
import java.util.Random;

public class RemyCorrect {
    public int N;
    public Node[] tree;


    public RemyCorrect(long n) {
        tree = new Node[2 * (int) n + 1];
        N = 0;

        tree[0] = new Node();
        N = 1;

        int randNoeud = new Random().nextInt(N); // savoir si c'est inclu ou pas, il ne faut pas que àa le soit

        double randFils = Math.random();

        //sauvegarde ancien noeud
        Node fg = tree[randNoeud];

        // init nouveau Noeud
        Node nvNoeud = new Node();

        if (randFils < 0.5) {
            nvNoeud.left_child = randNoeud;
            tree[N + 1] = new Node();
            nvNoeud.right_child = N + 1;
        } else {
            nvNoeud.right_child = randNoeud;
            tree[N + 1] = new Node();
            nvNoeud.left_child = N + 1;
        }
        nvNoeud.parent = tree[randNoeud].parent;
        tree[N] = nvNoeud;

        // Maj parent
        if (tree[tree[randNoeud].parent].left_child == randNoeud)
            tree[tree[randNoeud].parent].left_child = N;
        else
            tree[tree[randNoeud].parent].right_child = N;

        // Maj ancien noeud
        tree[randNoeud].parent = N;

        // Augmentation du nombre de noeuds
        N++;
        N++;

        tree[randNoeud] = new Node();
    }

}

    public RemyCorrect(long n, ArrayList<Integer> list) {
        long i, number;
        N = n;
        tree = new Node[2 * (int) n + 1];
        for (int ii = 0; ii < tree.length; ii++)
            tree[ii] = new Node();

        // built a tree of size 1
        tree[0].left_child = 1;
        tree[0].right_child = 2;
        tree[0].num = 1;
        tree[1].parent = tree[2].parent = 0;
        tree[1].right_child = tree[1].left_child = -1;
        tree[2].right_child = tree[2].left_child = -1;

        // the internal nodes will be in the boxes [0; i-1[ of the tree's array
        // the leaves in boxes [i, 2*i[ of the tree's array

        for (i = 2; i <= n; i++) {
            System.out.println("i=" + i);
            System.out.println("n=" + n);
            System.out.println("list[(int)i-2]*i = " + (list.get((int) i - 2) * i));
            System.out.println("(i-1) = " + (i - 1));
            number = (long) (list.get((int) i - 2)); // random number of [0, i[
            // the leaf is in the box number+i -1
            System.out.println("number = " + (number));
            System.out.println("(number+i-1) = " + (number + i - 1));
            change_leaves((int) (i - 1), (int) (number + i - 1));
            tree[(int) (i - 1)].right_child = (int) (2 * i - 1);
            tree[(int) (i - 1)].left_child = (int) (2 * i);

            tree[(int) (i - 1)].num = (int) i;
            tree[(int) (2 * i - 1)].parent = tree[(int) (2 * i)].parent = (int) (i - 1);
            tree[(int) (2 * i - 1)].right_child = tree[(int) (2 * i - 1)].left_child = -1;
            tree[(int) (2 * i)].right_child = tree[(int) (2 * i)].left_child = -1;
        }
    }

    // à check
    public boolean isLeaf(int i) {
        return tree[i].right_child == -1 && tree[i].left_child == -1;
    }

    private String phi(int i) {
        if (isLeaf(i))
            return "";
        else {
            return "(" + tree[i].left_child + ")" + phi(tree[i].right_child);
        }
    }

    public String phi() {
        return phi(0);
    }

    private long fact(long n) {
        if (n == 0) {
            return 1;
        }

        return fact(n - 1) * n;
    }

    public static int catalan(int n) {
        if (n == 0 || n == 1)
            return 1;

        double res = (2. * (2. * ((double) n) - 3.) / ((double) n)) * catalan(n - 1);

        return (int) res;
    }

    // nombre de Catalan
    public int catalan() {
        return catalan((int) N);
    }

    // test de couverture
    public static boolean coverageTests(int noeuds) {
        ;
        int diffTrees = catalan(noeuds);
        Remy[] differentTrees = new Remy[diffTrees];
        int[] counter = new int[diffTrees];
        boolean counted;
        int sizeOfDifferentTrees = 0;

        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<ArrayList<Integer>> liststmp = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        p.add(0);
        lists.add(p);
        for (int n = 1; n < noeuds - 1; n++) {
            for (int m = 0; m <= n; m++) {
                for (ArrayList v : lists) {
                    ArrayList<Integer> l = new ArrayList();
                    l.addAll(v);
                    l.add(m);
                    liststmp.add(l);
                }
            }
            lists = (ArrayList) liststmp.clone();
            liststmp = new ArrayList<>();
        }

        for (ArrayList<Integer> a : lists) {
            for (int v : a)
                System.out.print(v + " ");
            System.out.println();
        }

        for (int i = 0; i < lists.size(); i++) {

            System.out.println("list : " + lists.get(i));
            for (int a : lists.get(i)) {
                System.out.print(a + " ");
            }
            System.out.println();

            Remy remy = new Remy(noeuds, lists.get(i));

            // parcours des arbres déjà présents
            counted = false;
            for (int k = 0; k < sizeOfDifferentTrees; k++)
                if (differentTrees[k].phi().equals(remy.phi())) {
                    counter[k]++;
                    counted = true;
                }

            if (!counted) {
                differentTrees[sizeOfDifferentTrees] = remy;
                sizeOfDifferentTrees++;
            }
        }

        System.out.println("differentTrees.length " + differentTrees.length);

        // uniformity?
        for (int i = 0; i < differentTrees.length - 1; i++) {
            System.out.println(i + ": " + counter[i]);
            if (counter[i] != counter[i + 1])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Remy.coverageTests(6);
    }
}
