package com.NammaMetro.PaymentService.controller;

import com.NammaMetro.PaymentService.dto.PaymentDTO;
import com.NammaMetro.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/test")

    /**
     * Process a payment (POST /payments).
     */
    @PostMapping
    public PaymentDTO processPayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentService.processPayment(paymentDTO);
    }

    /**
     * Charge a penalty (POST /payments/penalty?userId=...).
     */
    @PostMapping("/penalty")
    public PaymentDTO chargePenalty(@RequestParam String userId) {
        return paymentService.chargePenalty(userId);
    }

    /**
     * Retrieve the last 5 transactions (GET /payments/history?userId=...).
     */
    @GetMapping("/history")
    public List<PaymentDTO> getPaymentHistory(@RequestParam String userId) {
        return paymentService.getPaymentHistory(userId);
    }
}
