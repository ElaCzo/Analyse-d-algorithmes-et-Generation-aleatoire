package BinaryHeap;

import java.util.ArrayList;
import java.util.Comparator;

public class MinBinaryHeap<T extends Comparable<T>> {
    ArrayList<T> heap;

    public MinBinaryHeap(){
        heap = new ArrayList<>();
    }

    public void permute(int i, int j){
        T tmp = heap.get(i);
        heap.remove(i);
        heap.add(i, heap.get(j));
        heap.remove(j);
        heap.add(j, tmp);
    }

    public MinBinaryHeap<T> add(T e){
        heap.add(e);

        int ind_e = heap.size()-1;
        while(ind_e!=0 && heap.get(ind_e).compareTo(heap.get(ind_e/2))>0){
            permute(ind_e, ind_e/2);
            ind_e = ind_e/2;
        }

        return this;
    }

    public void removeMin(){
        heap.remove(0);
    }
}
