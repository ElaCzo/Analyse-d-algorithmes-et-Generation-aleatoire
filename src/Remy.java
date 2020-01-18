import java.util.Random;

public class Remy {
    public long N;
    public Node[] tree;

    // construction of a growing binary tree with n internal nodes (n <= N)
    // modifies the postiion of two leaves, does not modify the tree built
    public void change_leaves(int a, int b){
        int parenta, parentb;
        parenta = tree[a].parent;
        parentb = tree[b].parent;

        if(tree[parenta].right_child == a)
            tree[parenta].right_child=b;
        else
            tree[parenta].left_child=b;
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
            number = (long) (list[(int)i-2] * i); // random number of [0, i[
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

    // à check
    public boolean isLeaf(int i){
       return tree[i].right_child==-1 && tree[i].left_child==-1;
    }

    public String phi(int i){
        if(isLeaf(i))
            return "";
        else {
            return "("+tree[i].left_child+")"+phi(tree[i].right_child);
        }
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
        if(n==0)
            return 1;

        int res = 0;
        for(int k = 0; k<n; k++){
            res += catalan(k)*catalan(n-k);
        }
        return res;
    }

    // nombre de Catalan
    public int catalan(){
        return catalan((int)N);
    }

    // test de couverture
    public boolean coverageTests(int NN) {
        Node[][] differentTrees = new Node[NN][];
        int[] counter;

        int[] list = new int[NN];

        for (int i = (int) N - 1; i >= 0; i--){
            for (int n = 0; n < N; n++) {
                list[i] = n;
                Remy remy = new Remy(NN, list);
                //differentTrees[n];
            }
        }

        return false;
    }

    public static void main(){

    }
}
