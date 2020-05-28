package com.ofa.epttavm.order.messaging;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class OrderPurchasedUserMessage implements Serializable {

    private final String username;
    private final String productCode;
    private final Boolean purchased;

    public OrderPurchasedUserMessage() {
        username = null;
        productCode = null;
        purchased = null;
    }
}
