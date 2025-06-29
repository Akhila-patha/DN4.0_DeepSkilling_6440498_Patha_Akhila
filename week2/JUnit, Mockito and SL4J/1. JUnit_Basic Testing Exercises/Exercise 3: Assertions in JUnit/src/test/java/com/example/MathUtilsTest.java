package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathUtilsTest {

    @Test
    public void testAdd() {
        MathUtils m = new MathUtils();
        assertEquals(9, m.add(4, 5));
    }

    @Test
    public void testSubtract() {
        MathUtils m = new MathUtils();
        assertEquals(1, m.subtract(5, 4));
    }

    @Test
    public void testMultiply() {
        MathUtils m = new MathUtils();
        assertEquals(20, m.multiply(4, 5));
    }

    @Test
    public void testDivide() {
        MathUtils m = new MathUtils();
        assertEquals(2, m.divide(10, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        MathUtils m = new MathUtils();
        m.divide(10, 0);
    }
}
