package com.dobudobu.file_service.Exception.ServiceCustomException;

public class CustomFileNotValid extends RuntimeException{

    public CustomFileNotValid(String message) {
        super(message);
    }

    public CustomFileNotValid(String message, Throwable cause) {
        super(message, cause);
    }
}
