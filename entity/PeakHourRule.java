package com.NammaMetro.PaymentService.entity;

import java.time.LocalTime;

public class PeakHourRule {

    private LocalTime startTime;
    private LocalTime endTime;
    private double multiplier;

    public PeakHourRule() {
    }

    public PeakHourRule(LocalTime startTime, LocalTime endTime, double multiplier) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.multiplier = multiplier;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getMultiplier() {
        return multiplier;
    }
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}
