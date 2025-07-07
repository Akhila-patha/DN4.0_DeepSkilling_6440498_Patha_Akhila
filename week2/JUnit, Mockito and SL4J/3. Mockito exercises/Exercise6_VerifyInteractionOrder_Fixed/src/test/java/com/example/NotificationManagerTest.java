package com.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.InOrder;

public class NotificationManagerTest {

    @Test
    public void testNotificationOrder() {
        NotificationService service = mock(NotificationService.class);
        NotificationManager manager = new NotificationManager(service);
        manager.notifyUser();

        InOrder inOrder = inOrder(service);
        inOrder.verify(service).sendEmail();
        inOrder.verify(service).sendSMS();
        inOrder.verify(service).pushNotification();
    }
}
