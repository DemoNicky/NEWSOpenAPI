package com.dobudobu.article_management_service.Exception.ServiceCustomException;

public class CustomExceptionUploadFileError extends RuntimeException{

    public CustomExceptionUploadFileError(String message) {
        super(message);
    }

    public CustomExceptionUploadFileError(String message, Throwable cause) {
        super(message, cause);
    }
}
