package com.NammaMetro.PaymentService.event;

import java.time.LocalDateTime;

public class TicketPaymentSuccessEvent {

    private Long paymentId;
    private String userId;
    private double finalAmount;
    private LocalDateTime paymentTime;

    public TicketPaymentSuccessEvent() {
    }

    public TicketPaymentSuccessEvent(Long paymentId, String userId, double finalAmount, LocalDateTime paymentTime) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.finalAmount = finalAmount;
        this.paymentTime = paymentTime;
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

    public double getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
}
