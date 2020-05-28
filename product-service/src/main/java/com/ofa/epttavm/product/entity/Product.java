package com.ofa.epttavm.product.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity(name = "product")
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock_count")
    private Integer stockCount;

    @Column(name = "category")
    private String category;
}
