package com.ofa.epttavm.user.service;

import com.ofa.epttavm.user.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    void loginUser(String username, String password);
    void logoutUser(String username);
    void addProductToBasket(String productCode, String username);
    void clearBasket(String username);
    User findUser(String username);
    List<User> findAllUsers();
}
