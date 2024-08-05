package com.ecommerce.project.exceptions;


public class APIException extends RuntimeException{
    private Long serionVersionUid = 1L;

    public APIException(String message) {
        super(message);
    }
}
