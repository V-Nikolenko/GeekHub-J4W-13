package org.geekhub.hw3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    public void testFail() {
        Assertions.assertTrue(false);
    }

    @Test
    public void testPass() {
        Assertions.assertTrue(true);
    }
}
