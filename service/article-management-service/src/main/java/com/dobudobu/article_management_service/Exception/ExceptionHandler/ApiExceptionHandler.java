package com.dobudobu.article_management_service.Exception.ExceptionHandler;

import com.dobudobu.article_management_service.Dto.Response.ResponseHandling;
import com.dobudobu.article_management_service.Exception.ServiceCustomException.CustomExceptionAlreadyExists;
import com.dobudobu.article_management_service.Exception.ServiceCustomException.CustomExceptionUploadFileError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseHandling<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setData(errors);
        responseHandling.setStatus("error");
        responseHandling.setHttpStatus(HttpStatus.BAD_REQUEST);
        responseHandling.setErrors(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHandling);
    }

    @ExceptionHandler(value = {CustomExceptionAlreadyExists.class})
    public ResponseEntity<Object> customExceptionAlreadyExists(CustomExceptionAlreadyExists customExceptionAlreadyExists){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customExceptionAlreadyExists.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }

    @ExceptionHandler(value = {CustomExceptionUploadFileError.class})
    public ResponseEntity<Object> customExceptionUploadFileError(CustomExceptionUploadFileError customExceptionUploadFileError){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customExceptionUploadFileError.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }
}
