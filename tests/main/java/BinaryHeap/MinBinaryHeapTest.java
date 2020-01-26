package main.java.BinaryHeap;

import org.quicktheories.core.Gen;
import org.quicktheories.core.stateful.Sequential;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import static org.junit.jupiter.api.Assertions.*;

class MinBinaryHeapTest {
    @org.junit.jupiter.api.Test
    void removeMin() {
        qt()
                .forAll(lists().of(integers().between(-5000, 5000)).ofSizeBetween(1, 5))
                .as((e) -> {
                    MinBinaryHeap<Integer> bh = new MinBinaryHeap<>();
                    for (int value : e)
                        bh.add(value);

                    System.out.println("heap avant remove : " + bh.heap);
                    return bh;
                })
                .check(bh -> {
                    if (!bh.heap.isEmpty()) {
                        ArrayList<Integer> l = new ArrayList<>();
                        l.addAll(bh.heap);
                        l.sort(Integer::compare);

                        System.out.println("list sorted : " + l);
                        int i = 0;
                        while (!bh.heap.isEmpty()) {
                            int min = bh.removeMin();
                            System.out.println("removemin : " + min);
                            System.out.println("l.get(" + i + ") = " + l.get(i));
                            if (min != l.get(i))
                                return false;
                            i++;

                            System.out.println();
                        }
                    }
                    return true;
                });
    }

    @org.junit.jupiter.api.Test
    void add() {
        qt()
                .forAll(lists().of(integers().between(-5000, 5000)).ofSizeBetween(1, 5))
                .as((e) -> {
                    MinBinaryHeap<Integer> bh = new MinBinaryHeap<>();
                    for (int value : e)
                        bh.add(value);
                    return bh;
                })
                .check(bh -> {
                    ArrayList<Integer> l = bh.heap;
                    if (bh.heap.size() == 0)
                        return true;
                    else {
                        Integer min = l.stream().min(Integer::compare).get();
                        System.out.println("min trouvé = " + l.get(0) + " vrai min :" + min);
                        return l.get(0) == min;
                    }
                });
    }

 /*   @org.junit.jupiter.api.Test
    void stability() {
        qt().forAll(commands()).as()
    }


        Gen<MinBinaryHeap<Integer>> heaps(){
       qt().forAll(integers().allPositive()).

        return lists().of(integers().all()).ofSizeBetween(0, 5)
                .zip()
                .as((e) -> {
                    MinBinaryHeap<Integer> bh = new MinBinaryHeap<>();
                    for(int value : e)
                        bh.add(value);
                    return bh;
                }).;
    }*/

    Gen<List<Integer>> commands() {
        return lists().of(integers().between(0, 1)).ofSizeBetween(0, 100);
    }

    Gen<Integer> values() {
        return integers().all();
    }


}