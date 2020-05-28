package com.ofa.epttavm.order.service;

import com.ofa.epttavm.order.client.UserClient;
import com.ofa.epttavm.order.dto.Product;
import com.ofa.epttavm.order.dto.User;
import com.ofa.epttavm.order.entity.Order;
import com.ofa.epttavm.order.exceptions.NoOpenSessionException;
import com.ofa.epttavm.order.exceptions.ProductOutOfStockException;
import com.ofa.epttavm.order.messaging.MessagePublisher;
import com.ofa.epttavm.order.messaging.OrderPurchasedProductMessage;
import com.ofa.epttavm.order.messaging.OrderPurchasedUserMessage;
import com.ofa.epttavm.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final MessagePublisher messagePublisher;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository,
                            final UserClient userClient,
                            final MessagePublisher messagePublisher) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.messagePublisher = messagePublisher;
    }

    /**
     * Call UserService to get user details, and check for any open session.
     * Force user to login or register into application.
     * Check count of each product in the basket to be sure it is in stock.
     * Finally, add product to user's basket by publishing a message.
     *
     * @param product
     * @param username
     */
    @Override
    public void addProductToBasket(Product product, String username) {
        User user = userClient.retrieveUserDetailsByUsername(username);
        if (user == null || !user.getLoggedIn()) {
            throw new NoOpenSessionException();
        }

        String productCode = product.getCode();
        if (product.getStockCount() < 1) {
            throw new ProductOutOfStockException(productCode);
        }

        messagePublisher.sendOrderPurchasedUserMessage(new OrderPurchasedUserMessage(username, productCode, false));
    }

    /**
     * Get the order information from repository and set its activity to false.
     * Publish a message to user service to clear user's basket.
     * Publish a message to product service to update count of purchased product.
     * @param username
     */
    @Override
    public void purchaseOrder(String username) {
        // TODO:
        // stock count of products in basket might be checked one more time.
        // it depends on the system design in real application environment.
        // storing copy of basket in order service is another idea to do that.

        User user = userClient.retrieveUserDetailsByUsername(username);
        Order order = new Order();
        order.setUsername(username);
        order.setEmail(user.getEmail());
        order.setAddress(user.getAddress());
        order.setCode(user.getId().toString() + Instant.now().toString());
        order.setProductCodes(user.getBasket());
        orderRepository.save(order);

        messagePublisher.sendOrderPurchasedUserMessage(new OrderPurchasedUserMessage(username, null, true));
        messagePublisher.sendOrderPurchasedProductMessage(new OrderPurchasedProductMessage(order.getProductCodes()));
    }

    @Override
    public List<Order> listOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
