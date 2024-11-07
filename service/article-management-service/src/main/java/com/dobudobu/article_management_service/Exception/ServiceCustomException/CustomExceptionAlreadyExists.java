package com.dobudobu.article_management_service.Exception.ServiceCustomException;

public class CustomExceptionAlreadyExists extends RuntimeException{

    public CustomExceptionAlreadyExists(String message) {
        super(message);
    }

    public CustomExceptionAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
