package com.ofa.epttavm.order.client;

import com.ofa.epttavm.order.dto.User;

public interface UserClient {
    User retrieveUserDetailsByUsername(final String username);
}
