package com.example;

public class PerformanceTester {
    public void performTask() {
        try {
            Thread.sleep(200); // Simulates a task that takes time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
