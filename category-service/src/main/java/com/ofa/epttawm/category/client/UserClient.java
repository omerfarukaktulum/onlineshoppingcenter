package com.ofa.epttawm.category.client;

import com.ofa.epttawm.category.dto.User;

public interface UserClient {
    User retrieveUserDetailsByUsername(final String username);
}
