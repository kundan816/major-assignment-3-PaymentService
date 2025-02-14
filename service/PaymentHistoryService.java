package com.NammaMetro.PaymentService.service;

import com.NammaMetro.PaymentService.dto.PaymentDTO;
import com.NammaMetro.PaymentService.entity.Payment;
import com.NammaMetro.PaymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentHistoryService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentDTO> getPaymentHistory(String userId) {
        List<Payment> payments = paymentRepository.findTop5ByUserIdOrderByCreatedAtDesc(userId);
        return payments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setUserId(payment.getUserId());
        dto.setPaymentType(payment.getPaymentType());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setAmount(payment.getAmount());
        dto.setDiscount(payment.getDiscount());
        dto.setFinalAmount(payment.getFinalAmount());
        dto.setQuantity(payment.getQuantity());
        dto.setSource(payment.getSource());
        dto.setDestination(payment.getDestination());
        dto.setPeakHourSurcharge(payment.getPeakHourSurcharge());
        dto.setCreatedAt(payment.getCreatedAt());
        return dto;
    }
}
