package com.dobudobu.gateway_server.Exception.ServiceCustomException;

public class CustomFailedAuthenticationException extends RuntimeException{

    public CustomFailedAuthenticationException(String message) {
        super(message);
    }

    public CustomFailedAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
