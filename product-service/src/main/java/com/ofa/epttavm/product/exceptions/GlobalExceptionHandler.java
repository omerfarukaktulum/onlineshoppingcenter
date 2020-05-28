package com.ofa.epttavm.product.exceptions;

import com.ofa.epttavm.product.dto.CustomHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserLoginOrRegisterException.class)
    @ResponseBody
    public CustomHttpResponse handleUserLoginOrRegisterException(UserLoginOrRegisterException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Login or register!");
    }

    @ExceptionHandler(UserNotAllowedForOperationException.class)
    @ResponseBody
    public CustomHttpResponse handleUserNotAllowedForOperationException(UserNotAllowedForOperationException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User is not allowed to do this operation!");
    }

    @ExceptionHandler(ProductIsOutOfStockException.class)
    @ResponseBody
    public CustomHttpResponse handleProductIsOutOfStockException(ProductIsOutOfStockException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product is out of stock :" + e.getMessage());
    }

    @ExceptionHandler(ProductDoesNotExistException.class)
    @ResponseBody
    public CustomHttpResponse handleProductDoesNotExceptException(ProductDoesNotExistException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product doest not exist!");
    }

    @ExceptionHandler(NullProductCodesException.class)
    @ResponseBody
    public CustomHttpResponse handlePNullProductCodesException(NullProductCodesException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product codes are null!");
    }
}