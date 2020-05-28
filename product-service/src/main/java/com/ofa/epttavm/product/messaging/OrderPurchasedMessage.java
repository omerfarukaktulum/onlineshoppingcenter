package com.ofa.epttavm.product.messaging;

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
public class OrderPurchasedMessage implements Serializable {

    private final List<String> productCodes;

    public OrderPurchasedMessage() {
        this.productCodes = null;
    }
}
