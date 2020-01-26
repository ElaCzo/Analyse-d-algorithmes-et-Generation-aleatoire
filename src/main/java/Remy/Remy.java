package main.java.Remy;

public class Remy {
    public long N;
    public Node[] tree;

    // construction of a growing binary tree with n internal nodes (n <= N)
    // modifies the position of two leaves, does not modify the tree built
    public void change_leaves(int a, int b){
        int parenta, parentb;
        parenta = tree[a].parent;
        parentb = tree[b].parent;

        if(tree[parenta].right_child == a)
            tree[parenta].right_child = b;
        else
            tree[parenta].left_child = b;

        tree[a].parent = parentb;

        if(tree[parentb].right_child == b)
            tree[parentb].right_child = a;
        else
            tree[parentb].left_child = a;

        tree[b].parent = parenta;
    }

    public Remy(long n) {
        long i, number;
        N=n;
        tree = new Node[2*(int)N +1];

        // built a tree of size 1
        tree[0].left_child = 1;
        tree[0].right_child = 2;
        tree[0].num = 1;
        tree[1].parent = tree[2].parent = 0;
        tree[1].right_child = tree[1].left_child = -1;
        tree[2].right_child = tree[2].left_child = -1;

        // the internal nodes will be in the boxes [0; i-1[ of the tree's array
        // the leaves in boxes [i, 2*i[ of the tree's array

        for(i=2; i<=n; i++){
            number = (long) (Math.random() * i); // random number of [0, i[
            // the leaf is in the box number+i -1
            change_leaves((int)(i-1), (int)(number+i-1));
            tree[(int)(i-1)].right_child = (int) (2*i-1);
            tree[(int)(i-1)].left_child = (int) (2*i);

            tree[(int)(i-1)].num = (int) i;
            tree[(int)(2*i-1)].parent = tree[(int)(2*i)].parent = (int)(i-1);
            tree[(int)(2*i-1)].right_child = tree[(int)(2*i-1)].left_child = -1;
            tree[(int)(2*i)].right_child = tree[(int)(2*i)].left_child = -1;
        }
    }

    public Remy(long n, int[] list) {
        long i, number;
        N=n;
        tree = new Node[2*(int)n +1];
        for(int ii=0; ii<tree.length; ii++)
            tree[ii]=new Node();

        // built a tree of size 1
        tree[0].left_child = 1;
        tree[0].right_child = 2;
        tree[0].num = 1;
        tree[1].parent = tree[2].parent = 0;
        tree[1].right_child = tree[1].left_child = -1;
        tree[2].right_child = tree[2].left_child = -1;

        // the internal nodes will be in the boxes [0; i-1[ of the tree's array
        // the leaves in boxes [i, 2*i[ of the tree's array

        for(i=2; i<=n; i++){
            System.out.println("i="+i);
            System.out.println("n="+n);
            System.out.println("list[(int)i-2]*i = "+(list[(int)i-2] * i));
            System.out.println("(i-1) = "+(i-1));
            number = (long) (list[(int)i-2]); // random number of [0, i[
            // the leaf is in the box number+i -1
            System.out.println("number = "+(number));
            System.out.println("(number+i-1) = "+(number+i-1));
            change_leaves((int)(i-1), (int)(number+i-1));
            tree[(int)(i-1)].right_child = (int) (2*i-1);
            tree[(int)(i-1)].left_child = (int) (2*i);

            tree[(int)(i-1)].num = (int) i;
            tree[(int)(2*i-1)].parent = tree[(int)(2*i)].parent = (int)(i-1);
            tree[(int)(2*i-1)].right_child = tree[(int)(2*i-1)].left_child = -1;
            tree[(int)(2*i)].right_child = tree[(int)(2*i)].left_child = -1;
        }
    }

    // à check
    public boolean isLeaf(int i){
       return tree[i].right_child==-1 && tree[i].left_child==-1;
    }

    private String phi(int i){
        if(isLeaf(i))
            return "";
        else {
            return "("+tree[i].left_child+")"+phi(tree[i].right_child);
        }
    }

    public String phi(){
        return phi(0);
    }

    private long fact(long n){
        if (n==0){
            return 1;
        }

        return fact(n-1)*n;
    }

    /*private int[][] createList(){
        int[][] lists = new int[(int)N][];


        int list[] = new int[(int)N];
        long nbLists = fact(N);
        for(int i=0; i < fact(N); i++){
            for()
            lists[i] = ;
        }

        return ;
    }*/

    /*private static boolean coverageTestsForSizeN(long n){
        Node[] differentTrees;
        int[] counter;
        Remy tree;

        for(int i=0 ; i<n ; i++)
            ;

    }*/

    public static int catalan(int n){
        if(n==0 || n==1)
            return 1;

        double res = (2. * (2. * ((double)n) - 3.) / ((double)n)) * catalan(n-1);

        return (int)res;
    }

    // nombre de Catalan
    public int catalan(){
        return catalan((int)N);
    }

    // test de couverture
    public static boolean coverageTests(int noeuds) { ;
        int diffTrees = catalan(noeuds);
        Remy[] differentTrees = new Remy[diffTrees];
        int[] counter = new int[diffTrees];
        boolean counted;
        int sizeOfDifferentTrees = 0;

        int[] list = new int[noeuds];

        for (int i = (int) noeuds - 1; i >= 0; i--){
            for (int n = 0; n <= i; n++) {
                list[i] = n;

                System.out.println("list : "+list);
                for(int a : list){
                    System.out.print(a+" ");
                }
                System.out.println();

                Remy remy = new Remy(noeuds, list);

                // parcours des arbres déjà présents
                counted = false;
                for(int k = 0; k < sizeOfDifferentTrees; k++)
                    if(differentTrees[k].phi().equals(remy.phi())){
                        counter[k]++;
                        counted = true;
                    }

                if(!counted){
                    differentTrees[sizeOfDifferentTrees]=remy;
                    sizeOfDifferentTrees++;
                }
            }
        }

        System.out.println("differentTrees.length "+ differentTrees.length);

        // uniformity?
        for(int i=0; i<differentTrees.length-1; i++) {
            System.out.println(i+": " + counter[i]);
            if (counter[i] != counter[i + 1])
                return false;
        }

        return true;
    }

    public static void main(String[] args){
        Remy.coverageTests(6);
    }
}
