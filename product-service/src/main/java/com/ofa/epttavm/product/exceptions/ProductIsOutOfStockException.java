package com.ofa.epttavm.product.exceptions;

public class ProductIsOutOfStockException extends RuntimeException {

    private final String productCode;

    public ProductIsOutOfStockException(final String productCode) {
        super(productCode);
        this.productCode = productCode;
    }
}
