package com.atrify.shop.exception;

import org.springframework.http.HttpStatus;

public class OrderNotExistsException extends RuntimeException{

    public OrderNotExistsException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
