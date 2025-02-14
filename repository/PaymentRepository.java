package com.NammaMetro.PaymentService.repository;

import com.NammaMetro.PaymentService.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findTop5ByUserIdOrderByCreatedAtDesc(String userId);
}
