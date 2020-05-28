package com.ofa.epttavm.user.service;

import com.ofa.epttavm.user.dto.UserRole;
import com.ofa.epttavm.user.entity.User;
import com.ofa.epttavm.user.exceptions.UserAlreadyExistsException;
import com.ofa.epttavm.user.exceptions.UserAlreadyLoginException;
import com.ofa.epttavm.user.exceptions.UserAlreadyLogoutException;
import com.ofa.epttavm.user.exceptions.WrongLoginCredentialsException;
import com.ofa.epttavm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Check username and email to avoid duplicated user credentials.
     * Assume that there is only an admin user with 'Admin' username.
     * @param user
     */
    @Override
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) == null
                && userRepository.findByEmail(user.getEmail()) == null) {
            if (user.getUsername().equals("Admin")) {
                user.setRole(UserRole.ADMIN);
            }
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public void loginUser(String username, String password){
        User user = userRepository.findByUsername(username);
        if (user != null && user.getLoggedIn()) {
            throw new UserAlreadyLoginException();
        }

        if (user != null && user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            userRepository.save(user);
        } else {
            throw new WrongLoginCredentialsException();
        }
    }

    @Override
    public void logoutUser(String username){
        User user = userRepository.findByUsername(username);
        if (user != null && user.getLoggedIn()) {
            user.setLoggedIn(false);
            userRepository.save(user);
        } else {
            throw new UserAlreadyLogoutException();
        }
    }

    @Override
    public void clearBasket(String username) {
        User user = userRepository.findByUsername(username);
        user.getBasket().clear();
        userRepository.save(user);
    }

    @Override
    public void addProductToBasket(String productCode, String username) {
        User user = userRepository.findByUsername(username);
        user.getBasket().add(productCode);
        userRepository.save(user);
    }

    @Override
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
