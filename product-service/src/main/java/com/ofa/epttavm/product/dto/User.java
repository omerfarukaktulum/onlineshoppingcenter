package com.ofa.epttavm.product.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class User {
    private final String username;
    private final String email;
    private final UserRole role;
    private final Boolean loggedIn;
    private final List<String> basket;

    public User() {
        username = null;
        email = null;
        role = null;
        loggedIn = false;
        basket = null;
    }
}