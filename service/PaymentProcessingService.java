package com.NammaMetro.PaymentService.service;

import com.NammaMetro.PaymentService.dto.PaymentDTO;
import com.NammaMetro.PaymentService.entity.Payment;
import com.NammaMetro.PaymentService.entity.PaymentMethod;
import com.NammaMetro.PaymentService.entity.PaymentType;
import com.NammaMetro.PaymentService.entity.PeakHourRule;
import com.NammaMetro.PaymentService.event.TicketPaymentSuccessEvent;
import com.NammaMetro.PaymentService.repository.PaymentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PaymentProcessingService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String exchange = "payment.exchange";
    private final String ticketPaymentRoutingKey = "ticket_payment_success";

    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        // Map DTO to entity
        Payment payment = new Payment();
        payment.setUserId(paymentDTO.getUserId());
        payment.setPaymentType(PaymentType.REGULAR);
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setAmount(paymentDTO.getAmount());
        payment.setQuantity(paymentDTO.getQuantity());
        payment.setSource(paymentDTO.getSource());
        payment.setDestination(paymentDTO.getDestination());

        // Calculate discount if paying by Metro Card
        double discount = 0;
        double finalAmount = payment.getAmount();
        if (payment.getPaymentMethod() == PaymentMethod.METRO_CARD) {
            discount = 0.10 * payment.getAmount(); // 10% discount
            finalAmount -= discount;
        }
        payment.setDiscount(discount);

        // Retrieve or set default peak hour rule
        PeakHourRule peakHourRule = (PeakHourRule) redisTemplate.opsForValue().get("peakHourRule");
        if (peakHourRule == null) {
            peakHourRule = new PeakHourRule(LocalTime.of(7, 0), LocalTime.of(10, 0), 1.5);
            redisTemplate.opsForValue().set("peakHourRule", peakHourRule);
        }

        // Check if current time is within peak hours
        LocalTime now = LocalTime.now();
        if (now.isAfter(peakHourRule.getStartTime()) && now.isBefore(peakHourRule.getEndTime())) {
            double surcharge = finalAmount * (peakHourRule.getMultiplier() - 1.0);
            finalAmount += surcharge;
            payment.setPeakHourSurcharge(surcharge);
        } else {
            payment.setPeakHourSurcharge(0);
        }

        payment.setFinalAmount(finalAmount);
        payment.setCreatedAt(LocalDateTime.now());

        // Save payment
        Payment savedPayment = paymentRepository.save(payment);

        // Publish ticket payment success event
        TicketPaymentSuccessEvent event = new TicketPaymentSuccessEvent(
                savedPayment.getId(),
                savedPayment.getUserId(),
                savedPayment.getFinalAmount(),
                savedPayment.getCreatedAt()
        );
        rabbitTemplate.convertAndSend(exchange, ticketPaymentRoutingKey, event);

        return convertToDTO(savedPayment);
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
