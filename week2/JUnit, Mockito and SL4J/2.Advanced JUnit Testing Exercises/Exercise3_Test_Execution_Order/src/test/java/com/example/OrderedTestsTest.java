package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTestsTest {

    @Test
    @Order(1)
    public void testA() {
        System.out.println("Running test A (Order 1)");
    }

    @Test
    @Order(2)
    public void testB() {
        System.out.println("Running test B (Order 2)");
    }

    @Test
    @Order(3)
    public void testC() {
        System.out.println("Running test C (Order 3)");
    }
}
