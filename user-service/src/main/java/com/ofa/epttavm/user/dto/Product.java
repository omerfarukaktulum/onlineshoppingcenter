package com.ofa.epttavm.user.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Product {
    private final Long id;
    private final String code;
    private final String name;
    private final Double price;
    private final Integer stockCount;
    private final String category;

    public Product() {
        id = null;
        code = null;
        name = null;
        price = -1.0;
        stockCount = -1;
        category = null;
    }
}