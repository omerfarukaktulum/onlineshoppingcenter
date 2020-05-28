package com.ofa.epttavm.product.messaging;

import com.ofa.epttavm.product.exceptions.NullProductCodesException;
import com.ofa.epttavm.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MessageSubscriber {

    private ProductService productService;

    @Autowired
    public MessageSubscriber(final ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "${order.product.queue}")
    void handleOrderPurchasedMessage(OrderPurchasedMessage message) {
        List<String> productCodes = message.getProductCodes();
        if (productCodes == null) {
            log.error("Message coming from order-service is null!");
            throw new NullProductCodesException();
        }

        log.info("Purchase message received, and stock counts will be updated.");
        for (String productCode : productCodes) {
            log.info("Product code: {}", productCode);
        }

        try{
            productService.decreaseProductCount(productCodes);
        }catch (final Exception e){
            log.error("Error when trying to process message", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
