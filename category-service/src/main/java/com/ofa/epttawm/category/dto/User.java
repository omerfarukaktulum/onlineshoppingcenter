package com.ofa.epttawm.category.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class User {
    private final String username;
    private final String email;
    private final UserRole role;
    private final Boolean loggedIn;

    public User() {
        username = null;
        email = null;
        role = null;
        loggedIn = false;
    }
}