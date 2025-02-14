package com.NammaMetro.PaymentService.service;

import com.NammaMetro.PaymentService.entity.PeakHourRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class PeakHourRuleService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String peakHourKey = "peakHourRule";

    public PeakHourRule getCurrentRule() {
        PeakHourRule rule = (PeakHourRule) redisTemplate.opsForValue().get(peakHourKey);
        if (rule == null) {
            // Default rule if none is set
            rule = new PeakHourRule(LocalTime.of(7, 0), LocalTime.of(10, 0), 1.5);
            redisTemplate.opsForValue().set(peakHourKey, rule);
        }
        return rule;
    }

    public PeakHourRule updateRule(PeakHourRule rule) {
        redisTemplate.opsForValue().set(peakHourKey, rule);
        return rule;
    }
}
