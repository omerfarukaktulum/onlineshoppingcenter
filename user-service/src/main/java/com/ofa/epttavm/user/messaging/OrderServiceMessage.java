package com.ofa.epttavm.user.messaging;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class OrderServiceMessage implements Serializable {

    private final String username;
    private final String productCode;
    private final Boolean purchased;

    public OrderServiceMessage() {
        username = null;
        productCode = null;
        purchased = null;
    }
}
