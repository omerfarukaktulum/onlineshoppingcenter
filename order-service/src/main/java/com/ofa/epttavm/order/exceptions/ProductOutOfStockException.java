package com.ofa.epttavm.order.exceptions;

public class ProductOutOfStockException extends RuntimeException {

    private final String productCode;

    public ProductOutOfStockException(final String productCode) {
        super(productCode);
        this.productCode = productCode;
    }
}
