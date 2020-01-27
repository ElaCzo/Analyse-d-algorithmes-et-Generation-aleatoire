package main.java.Remy;

import java.util.ArrayList;

public class Remy {
    public long N;
    public Node[] tree;

    // construction of a growing binary tree with n internal nodes (n <= N)
    // modifies the position of two leaves, does not modify the tree built
    public void change_leaves(int a, int b) {
        int parenta, parentb;

        parenta = tree[a].parent;
        parentb = tree[b].parent;

        if (tree[parenta].right_child == a)
            tree[parenta].right_child = b;
        else
            tree[parenta].left_child = b;

        tree[a].parent = parentb;

        if (tree[parentb].right_child == b)
            tree[parentb].right_child = a;
        else
            tree[parentb].left_child = a;

        tree[b].parent = parenta;
    }

    public Remy(long n) {
        long i, number;
        N = n;
        tree = new Node[2 * (int) n + 1];
        for (int ii = 0; ii < tree.length; ii++)
            tree[ii] = new Node();

        // built a tree of size 1
        tree[0].parent=-1;
        tree[0].left_child = 1;
        tree[0].right_child = 2;
        tree[0].num = 1;
        tree[1].parent = tree[2].parent = 0;
        tree[1].right_child = tree[1].left_child = -1;
        tree[2].right_child = tree[2].left_child = -1;

        // the internal nodes will be in the boxes [0; i-1[ of the tree's array
        // the leaves in boxes [i, 2*i[ of the tree's array

        for (i = 2; i <= n; i++) {
            number = (long) (Math.random() * i); // random number of [0, i[
            // the leaf is in the box number+i -1
            change_leaves((int) (i - 1), (int) (number + i - 1));
            tree[(int) (i - 1)].right_child = (int) (2 * i - 1);
            tree[(int) (i - 1)].left_child = (int) (2 * i);

            tree[(int) (i - 1)].num = (int) i;
            tree[(int) (2 * i - 1)].parent = tree[(int) (2 * i)].parent = (int) (i - 1);
            tree[(int) (2 * i - 1)].right_child = tree[(int) (2 * i - 1)].left_child = -1;
            tree[(int) (2 * i)].right_child = tree[(int) (2 * i)].left_child = -1;
        }
    }

    public Remy(long n, ArrayList<Integer> list) {
        long i, number;
        N = n;
        tree = new Node[2 * (int) n + 1];
        for (int ii = 0; ii < tree.length; ii++)
            tree[ii] = new Node();

        // built a tree of size 1
        tree[0].parent=-1;
        tree[0].left_child = 1;
        tree[0].right_child = 2;
        tree[0].num = 1;
        tree[1].parent = tree[2].parent = 0;
        tree[1].right_child = tree[1].left_child = -1;
        tree[2].right_child = tree[2].left_child = -1;

        // the internal nodes will be in the boxes [0; i-1[ of the tree's array
        // the leaves in boxes [i, 2*i[ of the tree's array

        for (i = 2; i <= n; i++) {
            //System.out.println("i=" + i);
            //System.out.println("n=" + n);
            //System.out.println("list[(int)i-2]*i = " + (list.get((int) i - 2) * i));
            //System.out.println("(i-1) = " + (i - 1));
            number = (long) (list.get((int) i - 2)); // random number of [0, i[
            // the leaf is in the box number+i -1
            //System.out.println("number = " + (number));
            //System.out.println("(number+i-1) = " + (number + i - 1));
            change_leaves((int) (i - 1), (int) (number + i - 1));
            tree[(int) (i - 1)].right_child = (int) (2 * i - 1);
            tree[(int) (i - 1)].left_child = (int) (2 * i);

            tree[(int) (i - 1)].num = (int) i;
            tree[(int) (2 * i - 1)].parent = tree[(int) (2 * i)].parent = (int) (i - 1);
            tree[(int) (2 * i - 1)].right_child = tree[(int) (2 * i - 1)].left_child = -1;
            tree[(int) (2 * i)].right_child = tree[(int) (2 * i)].left_child = -1;
        }
    }

    public boolean isLeaf(int i) {
        return tree[i].right_child == -1 && tree[i].left_child == -1;
    }

    private String phi(int i) {
        if (isLeaf(i))
            return "";
        else {
            return "(" + phi(tree[i].left_child) + ")" + phi(tree[i].right_child);
        }
    }

    public String phi() {
        int racine = 0;
        while (tree[racine].parent!=-1)
            racine=tree[racine].parent;
        return phi(racine);
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

        int res=0;
        for(int k=0; k<=n-1; k++){
            res+=catalan(k)*catalan(n-1-k);
        }

        return (int) res;
    }

    // nombre de Catalan
    public int catalan() {
        return catalan((int) N);
    }

    // test de couverture
    public static boolean coverageTests(int noeuds) {
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

        /*for (ArrayList<Integer> a : lists) {
            for(int v : a)
                System.out.print(v + " ");
            System.out.println();
        }*/

        for (int i = 0; i < lists.size(); i++) {
            //System.out.println("list : " + lists.get(i));
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

        //System.out.println("differentTrees.length " + differentTrees.length);
        //System.out.println("differentTrees " + differentTrees[0]);

        // uniformity?
        for (int i = 0; i < differentTrees.length - 1; i++) {
            System.out.println(i + ": " + counter[i]);
            if (counter[i] != counter[i + 1])
                return false;
        }

        System.out.println((differentTrees.length-1) + ": " + counter[differentTrees.length-1]);

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Résutat : " + Remy.coverageTests(6));
    }

    @Override
    public String toString() {
        return phi();
    }
}
