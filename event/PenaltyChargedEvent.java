package com.NammaMetro.PaymentService.event;

import java.time.LocalDateTime;

public class PenaltyChargedEvent {

    private Long paymentId;
    private String userId;
    private double penaltyAmount;
    private LocalDateTime chargedAt;

    public PenaltyChargedEvent() {
    }

    public PenaltyChargedEvent(Long paymentId, String userId, double penaltyAmount, LocalDateTime chargedAt) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.penaltyAmount = penaltyAmount;
        this.chargedAt = chargedAt;
    }

    // Getters and setters
    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }
    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public LocalDateTime getChargedAt() {
        return chargedAt;
    }
    public void setChargedAt(LocalDateTime chargedAt) {
        this.chargedAt = chargedAt;
    }
}
