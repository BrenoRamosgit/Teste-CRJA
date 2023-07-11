package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException{

    HttpStatus status;
    String message;

    public BaseException(HttpStatus status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

}
