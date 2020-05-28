package com.ofa.epttavm.order.exceptions;

import com.ofa.epttavm.order.dto.CustomHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoOpenSessionException.class)
    @ResponseBody
    public CustomHttpResponse handleNoOpenSessionException(NoOpenSessionException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "No active session, login or register!");
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    @ResponseBody
    public CustomHttpResponse handleProductOutOfStockException(ProductOutOfStockException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Product is out of stock :" + e.getMessage());
    }
}