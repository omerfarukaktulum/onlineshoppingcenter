package com.ofa.epttavm.order.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "orders")
public final class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_code")
    private String code;

    @Column(name = "username")
    private String username;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_address")
    private String address;

    @Column(name = "products")
    @ElementCollection(targetClass = String.class)
    private List<String> productCodes = new ArrayList<>();
}
