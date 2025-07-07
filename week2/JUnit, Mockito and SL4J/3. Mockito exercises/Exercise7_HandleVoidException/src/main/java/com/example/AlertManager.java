package com.example;

public class AlertManager {
    private AlertService alertService;

    public AlertManager(AlertService alertService) {
        this.alertService = alertService;
    }

    public void triggerAlert(String msg) {
        alertService.sendAlert(msg);
    }
}
