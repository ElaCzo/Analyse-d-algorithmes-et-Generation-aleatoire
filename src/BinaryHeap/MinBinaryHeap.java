package BinaryHeap;

import java.util.ArrayList;

public class MinBinaryHeap<T extends Comparable<T>> {
    ArrayList<T> heap;

    public MinBinaryHeap(){
        heap = new ArrayList<>();
    }

    public void swap(int i, int j){
        T tmp = heap.get(i);
        heap.remove(i);
        heap.add(i, heap.get(j));
        heap.remove(j);
        heap.add(j, tmp);
    }

    public MinBinaryHeap<T> removeMin(){
        swap(0, heap.size()-1);

        int ind_min = 0;

        boolean leftSonSmaller = (ind_min*2<heap.size()-1) &&
                (heap.get(ind_min).compareTo(heap.get(ind_min*2))>0);

        boolean rightSonSmaller = ((ind_min*2+1)<heap.size()-1) &&
                (heap.get(ind_min).compareTo(heap.get(ind_min*2+1))>0);

        while(leftSonSmaller || rightSonSmaller) {
            if(leftSonSmaller) {
                swap(ind_min, ind_min * 2);
                ind_min = ind_min * 2;
            }
            else if(rightSonSmaller){
                swap(ind_min, ind_min * 2 + 1);
                ind_min = ind_min * 2 + 1;
            }

            leftSonSmaller = (ind_min*2<heap.size()-1) &&
                    (heap.get(ind_min).compareTo(heap.get(ind_min*2))>0);

            rightSonSmaller = ((ind_min*2+1)<heap.size()-1) &&
                    (heap.get(ind_min).compareTo(heap.get(ind_min*2+1))>0);
        }

        return this;
    }

    public MinBinaryHeap<T> add(T e){
        heap.add(e);

        int ind_e = heap.size()-1;
        while(ind_e!=0 && heap.get(ind_e).compareTo(heap.get(ind_e/2))<0){
            swap(ind_e, ind_e/2);
            ind_e = ind_e/2;
        }

        return this;
    }
}
