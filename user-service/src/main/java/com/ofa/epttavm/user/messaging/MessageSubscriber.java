package com.ofa.epttavm.user.messaging;

import com.ofa.epttavm.user.exceptions.NullOrderServiceMessageException;
import com.ofa.epttavm.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSubscriber {

    private UserService userService;

    @Autowired
    public MessageSubscriber(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Two type of events are occurred in order-service as follows.
     *      - Add product to order (add product to basket of user)
     *      - Purchase existing order (clear basket of user)
     * @param message
     */
    @RabbitListener(queues = "${order.user.queue}")
    void handleOrderServiceMessage(OrderServiceMessage message) {
        if (message == null) {
            log.error("Message coming from order-service is null!");
            throw new NullOrderServiceMessageException();
        }
        String username = message.getUsername();
        String productCode = message.getProductCode();
        Boolean purchased = message.getPurchased();
        log.info("Purchase message received: {} - {} - {}", username, productCode, purchased);

        try{
            if (purchased) {
                userService.clearBasket(username);
            } else {
                userService.addProductToBasket(productCode, username);
            }
        }catch (final Exception e){
            log.error("Error while trying to handle message", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
