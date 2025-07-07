package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathUtilsTest {

    @Test
    void testAdd() {
        assertEquals(5, MathUtils.add(2, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(6, MathUtils.multiply(2, 3));
    }
}