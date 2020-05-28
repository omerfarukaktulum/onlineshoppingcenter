package com.ofa.epttawm.category.messaging;

import com.ofa.epttawm.category.exceptions.NullObjectsIncludedException;
import com.ofa.epttawm.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSubscriber {

    private CategoryService categoryService;

    @Autowired
    public MessageSubscriber(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RabbitListener(queues = "${product.category.queue}")
    void handleProductUpdatedMessage(ProductUpdatedMessage message) {
        String categoryCode = message.getCategoryCode();
        Integer stockCountDiff = message.getStockCountDifference();
        if (categoryCode == null || stockCountDiff == null) {
            log.error("Product-update message coming from product-service consists null objects!");
            throw new NullObjectsIncludedException();
        }

        log.info("Product-updated message received {} {}", categoryCode, stockCountDiff);

        try{
            categoryService.updateCategoryCount(categoryCode, stockCountDiff);
        }catch (final Exception e){
            log.error("Error while trying to process message", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
