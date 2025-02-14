package com.NammaMetro.PaymentService.service;

import com.NammaMetro.PaymentService.dto.PaymentDTO;
import com.NammaMetro.PaymentService.entity.Payment;
import com.NammaMetro.PaymentService.entity.PaymentType;
import com.NammaMetro.PaymentService.event.PenaltyChargedEvent;
import com.NammaMetro.PaymentService.repository.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PenaltyService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String exchange = "payment.exchange";
    private final String penaltyChargedRoutingKey = "penalty_charged";

    public PaymentDTO chargePenalty(String userId) {
        double penaltyAmount = 50.0; // fixed penalty

        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setPaymentType(PaymentType.PENALTY);
        payment.setAmount(penaltyAmount);
        payment.setDiscount(0);
        payment.setFinalAmount(penaltyAmount);
        payment.setCreatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        // Publish penalty charged event
        PenaltyChargedEvent event = new PenaltyChargedEvent(
                savedPayment.getId(),
                savedPayment.getUserId(),
                penaltyAmount,
                savedPayment.getCreatedAt()
        );
        rabbitTemplate.convertAndSend(exchange, penaltyChargedRoutingKey, event);

        return convertToDTO(savedPayment);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setUserId(payment.getUserId());
        dto.setPaymentType(payment.getPaymentType());
        dto.setAmount(payment.getAmount());
        dto.setDiscount(payment.getDiscount());
        dto.setFinalAmount(payment.getFinalAmount());
        dto.setCreatedAt(payment.getCreatedAt());
        return dto;
    }
}
