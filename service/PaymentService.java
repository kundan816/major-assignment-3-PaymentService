package com.NammaMetro.PaymentService.service;

import com.NammaMetro.PaymentService.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A facade that delegates to the specialized services
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentProcessingService paymentProcessingService;

    @Autowired
    private PenaltyService penaltyService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        return paymentProcessingService.processPayment(paymentDTO);
    }

    public PaymentDTO chargePenalty(String userId) {
        return penaltyService.chargePenalty(userId);
    }

    public List<PaymentDTO> getPaymentHistory(String userId) {
        return paymentHistoryService.getPaymentHistory(userId);
    }
}
