package main.java.BinaryHeap;

import java.util.ArrayList;

public class MinBinaryHeap<T extends Comparable<T>> {
    ArrayList<T> heap;

    public MinBinaryHeap(){
        heap = new ArrayList<>();
    }

    private void swap(int i, int j){
        //System.out.println("i="+i+" j="+j+" heap.size()="+heap.size());

        T tmp = heap.get(i);
        heap.remove(i);
        if(i<j)
            heap.add(i, heap.get(j-1));
        else
            heap.add(i, heap.get(j));

        heap.remove(j);
        heap.add(j, tmp);
    }

    public T removeMin(){
        if(heap.isEmpty())
            return null;
        else {
            System.out.println("heap.size() : "+(heap.size()));
            System.out.println("heap dans removeMin : "+heap);

            if(heap.size()>1)
                swap(0, heap.size() - 1);

            T min = heap.remove((int) (heap.size() - 1));
            int ind_min = 0;

            if(heap.size()>1) {
                boolean leftSonSmaller = ((ind_min * 2 + 1) < heap.size()) &&
                        (heap.get(ind_min).compareTo(heap.get(ind_min * 2 + 1)) > 0);

                boolean rightSonSmaller = ((ind_min * 2 + 2) < heap.size()) &&
                        (heap.get(ind_min).compareTo(heap.get(ind_min * 2 + 2)) > 0);

                while (leftSonSmaller || rightSonSmaller) {
                    if (leftSonSmaller) {
                        swap(ind_min, ind_min * 2 + 1);
                        ind_min = ind_min * 2 + 1;
                    } else if (rightSonSmaller) {
                        swap(ind_min, ind_min * 2 + 2);
                        ind_min = ind_min * 2 + 2;
                    }

                    leftSonSmaller = ((ind_min * 2 + 1) < heap.size()) &&
                            (heap.get(ind_min).compareTo(heap.get(ind_min * 2 + 1)) > 0);

                    rightSonSmaller = ((ind_min * 2 + 2) < heap.size()) &&
                            (heap.get(ind_min).compareTo(heap.get(ind_min * 2 + 2)) > 0);
                }
            }
            return min;
        }
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
