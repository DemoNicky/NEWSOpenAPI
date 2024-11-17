package com.dobudobu.user_service.Exception.ExceptionHandler;

import com.dobudobu.user_service.Dto.Response.ResponseHandling;
import com.dobudobu.user_service.Exception.ServiceCustomException.CustomAuthenticationFailed;
import com.dobudobu.user_service.Exception.ServiceCustomException.CustomExceptionAlreadyExsist;
import com.dobudobu.user_service.Exception.ServiceCustomException.CustomExceptionUploadFileError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

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

    @ExceptionHandler(value = {CustomExceptionAlreadyExsist.class})
    public ResponseEntity<Object> customExceptionAlreadyExsist(CustomExceptionAlreadyExsist customExceptionAlreadyExsist){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customExceptionAlreadyExsist.getMessage());
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

    @ExceptionHandler(value = {CustomAuthenticationFailed.class})
    public ResponseEntity<Object> customAuthenticationFailed(CustomAuthenticationFailed customAuthenticationFailed){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customAuthenticationFailed.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }

}
