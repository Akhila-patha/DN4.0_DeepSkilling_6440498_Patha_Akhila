package com.example;

public class NotificationManager {
    private NotificationService service;

    public NotificationManager(NotificationService service) {
        this.service = service;
    }

    public void notifyUser() {
        service.sendEmail();
        service.sendSMS();
        service.pushNotification();
    }
}
