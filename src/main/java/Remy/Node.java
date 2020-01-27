package main.java.Remy;

public class Node {
    int right_child;
    int left_child;
    int parent;
    public static int cpt=0;
    int num;

    public Node(){
        num=cpt;
        this.left_child=-1;
        this.right_child=-1;
        cpt++;
    }

    public Node(int num){
        this.num=num;
    }

    public Node(int fg, int fd){
        num=cpt;
        cpt++;
        this.left_child=fg;
        this.right_child=fd;
    }

    @Override
    public String toString() {
        return num+") fg="+left_child+" fd="+right_child+" parent="+parent;
    }
}
