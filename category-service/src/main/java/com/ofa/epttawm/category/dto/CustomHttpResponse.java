package com.ofa.epttawm.category.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class CustomHttpResponse {
    private int code;
    private String message;
    private Instant timestamp;

    public CustomHttpResponse(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
        this.timestamp = Instant.now();
    }
}
