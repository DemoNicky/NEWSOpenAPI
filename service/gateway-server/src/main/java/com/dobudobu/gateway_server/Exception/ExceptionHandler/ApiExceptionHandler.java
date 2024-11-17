package com.dobudobu.gateway_server.Exception.ExceptionHandler;

import com.dobudobu.gateway_server.Dto.Response.ResponseHandling;
import com.dobudobu.gateway_server.Exception.ServiceCustomException.CustomFailedAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {CustomFailedAuthenticationException.class})
    public ResponseEntity<Object> customMissingAuthentication(CustomFailedAuthenticationException customFailedAuthenticationException){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ResponseHandling responseHandling = new ResponseHandling();
        responseHandling.setStatus("error");
        responseHandling.setMessage(customFailedAuthenticationException.getMessage());
        responseHandling.setHttpStatus(httpStatus);
        responseHandling.setErrors(true);
        return new ResponseEntity<>(responseHandling, httpStatus);
    }
}
