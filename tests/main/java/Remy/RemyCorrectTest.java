package main.java.Remy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemyCorrectTest {

    @Test
    void catalan() {
        assertEquals(1, RemyCorrect.catalan(0));
        assertEquals(1, RemyCorrect.catalan(1));
        assertEquals(2, RemyCorrect.catalan(2));
        assertEquals(5, RemyCorrect.catalan(3));
        assertEquals(14, RemyCorrect.catalan(4));
    }

    @Test
    void testCatalan() {
    }
}