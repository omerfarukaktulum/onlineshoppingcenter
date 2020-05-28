package com.ofa.epttavm.product.client;

import com.ofa.epttavm.product.dto.User;

public interface UserClient {
    User retrieveUserDetailsByUsername(final String username);
}
