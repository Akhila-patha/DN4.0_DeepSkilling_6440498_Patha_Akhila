package com.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlertManagerTest {

    @Test
    public void testSendAlertThrowsException() {
        AlertService mockService = mock(AlertService.class);

        doThrow(new RuntimeException("Alert failed"))
            .when(mockService).sendAlert("System down");

        AlertManager manager = new AlertManager(mockService);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            manager.triggerAlert("System down");
        });

        assertEquals("Alert failed", thrown.getMessage());

        verify(mockService).sendAlert("System down");
    }
}
