package com.atrify.shop.exception;

import org.springframework.http.HttpStatus;

public class OrderAlreadyExistsException extends RuntimeException{

    public OrderAlreadyExistsException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
