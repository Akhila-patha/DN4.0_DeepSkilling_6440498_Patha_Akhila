package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

public class CalculatorServiceTest {

    @Test
    public void testAddWithArgumentMatching() {
        CalculatorService mockService = mock(CalculatorService.class);
        mockService.add(5, 10);
        verify(mockService).add(eq(5), anyInt());
    }
}