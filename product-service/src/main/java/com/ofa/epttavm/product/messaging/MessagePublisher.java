package com.ofa.epttavm.product.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    private RabbitTemplate rabbitTemplate;
    private String categoryExchange;
    private String categoryRoutingKey;

    @Autowired
    public MessagePublisher(final RabbitTemplate rabbitTemplate,
                            @Value("${product.category.exchange}") final String categoryExchange,
                            @Value("${product.category.key}") final String categoryRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.categoryExchange = categoryExchange;
        this.categoryRoutingKey = categoryRoutingKey;
    }

    public void sendCategoryCountUpdateMessage(final ProductUpdatedMessage event) {
        rabbitTemplate.convertAndSend(categoryExchange, categoryRoutingKey, event);
    }
}
