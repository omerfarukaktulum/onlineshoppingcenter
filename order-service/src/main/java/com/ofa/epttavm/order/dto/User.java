package com.ofa.epttavm.order.dto;

import com.ofa.epttavm.order.dto.enums.UserRole;
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
    private final Long id;
    private final String username;
    private final String email;
    private final String address;
    private final UserRole role;
    private final Boolean loggedIn;
    private final List<String> basket;

    public User() {
        id = null;
        username = null;
        email = null;
        address = null;
        role = null;
        loggedIn = false;
        basket = null;
    }
}