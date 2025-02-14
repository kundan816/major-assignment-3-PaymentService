package com.NammaMetro.PaymentService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "payment.exchange";
    public static final String TICKET_PAYMENT_QUEUE = "ticket_payment_success";
    public static final String PENALTY_CHARGED_QUEUE = "penalty_charged";

    @Bean
    public DirectExchange paymentExchange() {
        // By default, this is a non-durable exchange
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue ticketPaymentQueue() {
        return new Queue(TICKET_PAYMENT_QUEUE, false);
    }

    @Bean
    public Queue penaltyChargedQueue() {
        return new Queue(PENALTY_CHARGED_QUEUE, false);
    }

    @Bean
    public Binding ticketPaymentBinding(Queue ticketPaymentQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(ticketPaymentQueue)
                .to(paymentExchange)
                .with(TICKET_PAYMENT_QUEUE);
    }

    @Bean
    public Binding penaltyChargedBinding(Queue penaltyChargedQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(penaltyChargedQueue)
                .to(paymentExchange)
                .with(PENALTY_CHARGED_QUEUE);
    }
}
