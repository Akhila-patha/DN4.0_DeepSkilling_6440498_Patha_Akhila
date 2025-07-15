package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingDemo {

    // Obtain a logger for this class
    private static final Logger log = LoggerFactory.getLogger(ParameterizedLoggingDemo.class);

    public static void main(String[] args) {

        // Simple parameter substitution ({} placeholders)
        String user = "Alice";
        int items = 3;
        log.info("User {} added {} items to the cart.", user, items);

        // Logging an exception with parameters
        try {
            int result = divide(10, 0);
            log.debug("Division result: {}", result);  // won’t run
        } catch (ArithmeticException ex) {
            log.error("Error dividing numbers: numerator={}, denominator={}", 10, 0, ex);
        }

        // Lazy‑evaluated expensive message — computed only if level is enabled
        if (log.isDebugEnabled()) {
            log.debug("Expensive calculation result: {}", heavyComputation());
        }
    }

    private static int divide(int a, int b) {
        return a / b; // will throw ArithmeticException for b == 0
    }

    private static String heavyComputation() {
        // Simulate costly work
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        return "42";
    }
}
