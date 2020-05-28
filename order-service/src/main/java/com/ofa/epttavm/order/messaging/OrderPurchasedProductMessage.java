package com.ofa.epttavm.order.messaging;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class OrderPurchasedProductMessage implements Serializable {

    private final List<String> productCodes;

    public OrderPurchasedProductMessage() {
        productCodes = null;
    }
}
