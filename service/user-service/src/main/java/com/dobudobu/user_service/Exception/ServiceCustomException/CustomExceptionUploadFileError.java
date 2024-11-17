package com.dobudobu.user_service.Exception.ServiceCustomException;

public class CustomExceptionUploadFileError extends RuntimeException{

    public CustomExceptionUploadFileError(String message) {
        super(message);
    }

    public CustomExceptionUploadFileError(String message, Throwable cause) {
        super(message, cause);
    }
}
