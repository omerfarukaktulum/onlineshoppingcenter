package com.ofa.epttavm.order.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    private RabbitTemplate rabbitTemplate;
    private String userExchange;
    private String userRoutingKey;
    private String productExchange;
    private String productRoutingKey;

    @Autowired
    public MessagePublisher(final RabbitTemplate rabbitTemplate,
                            @Value("${order.user.exchange}") final String userExchange,
                            @Value("${order.user.key}") final String userRoutingKey,
                            @Value("${order.product.exchange}") final String productExchange,
                            @Value("${order.product.key}") final String productRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.userExchange = userExchange;
        this.userRoutingKey = userRoutingKey;
        this.productExchange = productExchange;
        this.productRoutingKey = productRoutingKey;
    }

    public void sendOrderPurchasedUserMessage(final OrderPurchasedUserMessage event) {
        rabbitTemplate.convertAndSend(userExchange, userRoutingKey, event);
    }

    public void sendOrderPurchasedProductMessage(final OrderPurchasedProductMessage event) {
        rabbitTemplate.convertAndSend(productExchange, productRoutingKey, event);
    }

}
