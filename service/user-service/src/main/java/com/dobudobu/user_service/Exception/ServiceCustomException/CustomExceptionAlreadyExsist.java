package com.dobudobu.user_service.Exception.ServiceCustomException;

public class CustomExceptionAlreadyExsist extends RuntimeException{

    public CustomExceptionAlreadyExsist(String message) {
        super(message);
    }

    public CustomExceptionAlreadyExsist(String message, Throwable cause) {
        super(message, cause);
    }
}
