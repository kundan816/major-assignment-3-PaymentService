package com.NammaMetro.PaymentService.dto;

import com.NammaMetro.PaymentService.entity.PaymentMethod;
import com.NammaMetro.PaymentService.entity.PaymentType;

import java.time.LocalDateTime;

public class PaymentDTO {

    private Long id;
    private String userId;
    private PaymentType paymentType;
    private PaymentMethod paymentMethod;
    private double amount;
    private double discount;
    private double finalAmount;
    private int quantity;
    private String source;
    private String destination;
    private double peakHourSurcharge;
    private LocalDateTime createdAt;

    public PaymentDTO() {
    }

    // Getters and setters...
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPeakHourSurcharge() {
        return peakHourSurcharge;
    }
    public void setPeakHourSurcharge(double peakHourSurcharge) {
        this.peakHourSurcharge = peakHourSurcharge;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
