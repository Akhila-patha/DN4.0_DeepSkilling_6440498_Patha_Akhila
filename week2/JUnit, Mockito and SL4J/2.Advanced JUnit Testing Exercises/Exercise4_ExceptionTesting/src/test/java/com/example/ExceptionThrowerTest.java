package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionThrowerTest {

    @Test
    public void testThrowException_ThrowsIllegalArgumentException() {
        ExceptionThrower et = new ExceptionThrower();

        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> et.throwException(null),
            "Expected throwException(null) to throw IllegalArgumentException"
        );

        assertEquals("Input must not be null", thrown.getMessage());
    }

    @Test
    public void testThrowException_NoExceptionForValidInput() {
        ExceptionThrower et = new ExceptionThrower();

        assertDoesNotThrow(() -> et.throwException("hello"));
    }
}
