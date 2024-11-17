package com.dobudobu.user_service.Exception.ServiceCustomException;

public class CustomAuthenticationFailed extends RuntimeException{

    public CustomAuthenticationFailed(String message) {
        super(message);
    }

    public CustomAuthenticationFailed(String message, Throwable cause) {
        super(message, cause);
    }
}
