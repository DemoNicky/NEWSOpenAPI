package com.dobudobu.file_service.Exception.ExceptionHandler;

import com.dobudobu.file_service.Dto.Response.ResponseHandling;
import com.dobudobu.file_service.Exception.ServiceCustomException.CustomFailedUploadFile;
import com.dobudobu.file_service.Exception.ServiceCustomException.CustomFileNotValid;
import com.dobudobu.file_service.Exception.ServiceCustomException.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptiionHandler {

    @ExceptionHandler(value = {CustomFailedUploadFile.class})
    public ResponseEntity<Object> customFailUploadException(CustomFailedUploadFile customFailUploadException){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customFailUploadException.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }

    @ExceptionHandler(value = {CustomNotFoundException.class})
    public ResponseEntity<Object> customNotFoundException(CustomNotFoundException customNotFoundException){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customNotFoundException.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }

    @ExceptionHandler(value = {CustomFileNotValid.class})
    public ResponseEntity<Object> customFileNotValid(CustomFileNotValid customFileNotValid){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customFileNotValid.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }
}
