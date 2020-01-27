package main.java.Remy;

import java.util.ArrayList;

public class RemyCorrect {
    public int N;
    public Node[] tree;
    public int root;

    public void add(ArrayList<Integer> list, int n) {
        int randNoeud = list.get(N-1); // savoir si c'est inclu ou pas, il ne faut pas que ça le soit

        // init nouveau Noeud
        Node nvNoeud = new Node(-1, -1);

        if (list.get((n-1)+N-1) < 0.5) {
            nvNoeud.left_child = randNoeud;
            tree[N + 1] = new Node(-1, -1);
            tree[N + 1].parent = N;
            nvNoeud.right_child = N + 1;
        } else {
            nvNoeud.right_child = randNoeud;
            tree[N + 1] = new Node(-1, -1);
            tree[N + 1].parent = N;
            nvNoeud.left_child = N + 1;
        }

        nvNoeud.parent = tree[randNoeud].parent;
        tree[N] = nvNoeud;

        // Maj parent
        if(tree[randNoeud].parent!=-1) {
            if (tree[tree[randNoeud].parent].left_child == randNoeud)
                tree[tree[randNoeud].parent].left_child = N;
            else
                tree[tree[randNoeud].parent].right_child = N;
        }

        // Maj ancien noeud
        tree[randNoeud].parent = N;

        // Augmentation du nombre de noeuds
        N++;
        N++;

        System.out.println("tree à la fin de add : "+tree);
        for(int i=0; i<tree.length; i++)
            System.out.println(tree[i]);
    }

    public RemyCorrect(int n, ArrayList<Integer> list) {
        tree = new Node[2 * (int) n + 1];

        root = 0;
        tree[0]=new Node();
        tree[0].parent=-1;
        for(int i=1; i <= 2*n-1 ; i+=2){
            int hit = list.get(i-1);
            int direction = list.get((2*n)+(i-1));
            int parent = tree[hit].parent;
            if(parent==-1)
                root=i;
            else if(tree[parent].left_child==hit)
                tree[parent].left_child=i;
            else
                tree[parent].right_child=i;

            tree[i]=new Node();
            tree[i].parent = parent;
            if(direction==0){
                tree[i].left_child = i+1;
                tree[i].right_child = hit;
            }
            else {
                tree[i].left_child=hit;
                tree[i].right_child=i+1;
            }

            tree[hit].parent=i;
            tree[i+1]= new Node();
            tree[i+1].left_child=-1;
            tree[i+1].right_child=-1;
            tree[i+1].parent=i;
        }

        N=2*n+1;
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
        return phi(root);
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

        RemyCorrect[] differentTrees = new RemyCorrect[diffTrees];
        int[] counter = new int[diffTrees];
        boolean counted;
        int sizeOfDifferentTrees = 0;

        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<ArrayList<Integer>> liststmp = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        p.add(0);
        lists.add(p);
        for (int n = 1; n < 2*noeuds ; n++) {
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

        // Ajout valeurs 0 et 1 pour le 2e tirage
        for (int n = 0; n < 2 * noeuds ; n++) {
            for (int m = 0; m <= 1; m++) {
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

            Node.cpt=0;
            RemyCorrect remy = new RemyCorrect(noeuds, lists.get(i));


            System.out.println("remy: "+remy.phi());
            System.out.println(remy.tree);
            for(Node n : remy.tree)
                System.out.println(n);

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
            if (counter[i] != counter[i + 1]) {
                System.out.println((i+1) + ": " + counter[i+1]);
                return false;
            }
        }

        System.out.println((differentTrees.length-1) + ": " + counter[differentTrees.length-1]);


        return true;
    }

    public static void main(String[] args) {
        System.out.println("Rés : "+ RemyCorrect.coverageTests(3));
    }
}
