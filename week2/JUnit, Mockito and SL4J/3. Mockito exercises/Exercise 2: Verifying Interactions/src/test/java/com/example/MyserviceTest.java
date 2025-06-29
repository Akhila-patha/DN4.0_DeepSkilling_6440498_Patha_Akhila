package com.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MyServiceTest {

    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Some Data");

        MyService service = new MyService(mockApi);
        service.fetchData();

        verify(mockApi).getData(); // Verifies getData() was called
    }
}
