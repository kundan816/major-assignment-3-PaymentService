package com.NammaMetro.PaymentService.exception;

public class RedisCacheException extends RuntimeException {
    public RedisCacheException(String message) {
        super(message);
    }
}
