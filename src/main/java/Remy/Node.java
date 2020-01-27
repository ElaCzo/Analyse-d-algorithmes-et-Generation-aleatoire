package main.java.Remy;

public class Node {
    int right_child;
    int left_child;
    int parent;
    static int cpt=0;
    int num;

    public Node(){
        num=cpt;
        cpt++;
    }

    public Node(int num){
        this.num=num;
    }
}
