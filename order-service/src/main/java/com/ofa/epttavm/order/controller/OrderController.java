package com.ofa.epttavm.order.controller;

import com.ofa.epttavm.order.dto.CustomHttpResponse;
import com.ofa.epttavm.order.dto.Product;
import com.ofa.epttavm.order.entity.Order;
import com.ofa.epttavm.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value="Order Service", tags = {"order-service"})
@SwaggerDefinition(tags = {@Tag(
        name = "order-service",
        description = "Order and purchase operations are handled in this service.")
})
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Add product to basket.")
    @PostMapping("/product")
    public @ResponseBody CustomHttpResponse add(@RequestBody final Product product, @RequestParam final String username) {
        orderService.addProductToBasket(product, username);
        return new CustomHttpResponse(HttpStatus.OK, "The product is added to basket!");
    }

    @ApiOperation(value = "Purchase products in the basket.")
    @PostMapping("/purchase")
    public @ResponseBody CustomHttpResponse purchase(@RequestParam final String username) {
        orderService.purchaseOrder(username);
        return new CustomHttpResponse(HttpStatus.OK, "The products are purchased successfully!");
    }


    @ApiOperation(value = "List orders of a given user or all.")
    @GetMapping("/")
    public List<Order> listOrders(@RequestParam(required = false, defaultValue = "") final String username) {
        return orderService.listOrders(username);
    }
}
