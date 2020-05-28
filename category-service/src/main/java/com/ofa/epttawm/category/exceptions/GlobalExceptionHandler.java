package com.ofa.epttawm.category.exceptions;

import com.ofa.epttawm.category.dto.CustomHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserSessionClosedException.class)
    @ResponseBody
    public CustomHttpResponse handleUserSessionClosedException(UserSessionClosedException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User must login first!");
    }

    @ExceptionHandler(UserNotAllowedForOperationException.class)
    @ResponseBody
    public CustomHttpResponse handleUserNotAllowedForOperationException(UserNotAllowedForOperationException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User is not allowed to do this operation!");
    }

    @ExceptionHandler(CategoryCodeAlreadyExistsException.class)
    @ResponseBody
    public CustomHttpResponse handleCategoryCodeAlreadyExistsException(CategoryCodeAlreadyExistsException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Category code already exists!");
    }

    @ExceptionHandler(NullObjectsIncludedException.class)
    @ResponseBody
    public CustomHttpResponse handleNullStockCountException(NullObjectsIncludedException e) {
        log.info("{}", e);
        return new CustomHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Stock count is null!");
    }
}