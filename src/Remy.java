import java.util.Random;

public class Remy {
    int N = 1000;
    Node[] tree = new Node[2*N +1];

    // construction of a growing binary tree with n internal nodes (n <= N)
    // modifies the postiion of two leaves, does not modify the tree built
    void change_leaves(int a, int b){
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

    void growing_tree(long n) {
        long i, number, tmp;

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

    public static void main(){

    }
}
