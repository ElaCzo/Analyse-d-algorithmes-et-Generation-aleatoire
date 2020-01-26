package main.java.BinaryHeap;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import static org.junit.jupiter.api.Assertions.*;

class MinBinaryHeapTest {

    @org.junit.jupiter.api.Test
    void removeMin() {
        qt()
                .forAll(integers().allPositive()
                        , integers().allPositive())
                .check((i,j) -> i + j > 0);
    }

    @org.junit.jupiter.api.Test
    void add() {
    }
}