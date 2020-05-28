package com.ofa.epttavm.user.controller;

import com.ofa.epttavm.user.dto.CustomHttpResponse;
import com.ofa.epttavm.user.entity.User;
import com.ofa.epttavm.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="User Service", tags = {"user-service"})
@SwaggerDefinition(tags = {@Tag(
        name = "user-service",
        description = "User-related operations are handled in this service.")
})
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Register a new user.")
    @PostMapping("/register")
    public @ResponseBody CustomHttpResponse register(@RequestBody final User user) {
        userService.registerUser(user);
        return new CustomHttpResponse(HttpStatus.OK, "User registration is successful!");
    }

    @ApiOperation(value = "Login an existing user.")
    @PostMapping("/login")
    public @ResponseBody CustomHttpResponse login(@RequestParam final String username, @RequestParam final String password) {
        userService.loginUser(username, password);
        return new CustomHttpResponse(HttpStatus.OK,"Login successful!");
    }

    @ApiOperation(value = "Logout an existing user.")
    @PostMapping("/logout")
    public @ResponseBody CustomHttpResponse logout(@RequestParam final String username) {
        userService.logoutUser(username);
        return new CustomHttpResponse(HttpStatus.OK,"Logout successful!");
    }

    @ApiOperation(value = "List a user with given username.")
    @GetMapping("/{username}")
    public User get(@PathVariable(name = "username") final String username) {
        return userService.findUser(username);
    }

    @ApiOperation(value = "List all users.")
    @GetMapping("/")
    public List<User> getAll() {
        return userService.findAllUsers();
    }
}