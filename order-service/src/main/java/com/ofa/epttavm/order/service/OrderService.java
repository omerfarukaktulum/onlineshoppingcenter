package com.ofa.epttavm.order.service;

import com.ofa.epttavm.order.dto.Product;
import com.ofa.epttavm.order.entity.Order;

import java.util.List;

public interface OrderService {
    void addProductToBasket(Product product, String username);
    void purchaseOrder(String username);
    List<Order> listOrders(String username);
}
