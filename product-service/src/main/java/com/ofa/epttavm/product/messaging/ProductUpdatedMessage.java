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
public class ProductUpdatedMessage implements Serializable {

    private final String categoryCode;
    private final Integer stockCountDifference;

    public ProductUpdatedMessage() {
        this.categoryCode = null;
        this.stockCountDifference = null;
    }
}
