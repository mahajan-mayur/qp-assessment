package com.qp.groceryapi.exception;

import lombok.Builder;


public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
