package com.ofa.epttavm.user.exceptions;

import com.ofa.epttavm.user.dto.CustomHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    public CustomHttpResponse handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User already exists!");
    }

    @ExceptionHandler(WrongLoginCredentialsException.class)
    @ResponseBody
    public CustomHttpResponse handleWrongLoginCredentialsException(WrongLoginCredentialsException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Username and/or password is wrong!");
    }

    @ExceptionHandler(UserAlreadyLogoutException.class)
    @ResponseBody
    public CustomHttpResponse handleUserAlreadyLogoutException(UserAlreadyLogoutException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User already logout!");
    }

    @ExceptionHandler(UserAlreadyLoginException.class)
    @ResponseBody
    public CustomHttpResponse handleUserAlreadyLogoutException(UserAlreadyLoginException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User already login!");
    }

    @ExceptionHandler(NullOrderServiceMessageException.class)
    @ResponseBody
    public CustomHttpResponse handleNullOrderServiceMessageException(NullOrderServiceMessageException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Order service message is null!");
    }
}