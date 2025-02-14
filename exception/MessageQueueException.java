package com.NammaMetro.PaymentService.exception;

public class MessageQueueException extends RuntimeException {
    public MessageQueueException(String message) {
        super(message);
    }
}
