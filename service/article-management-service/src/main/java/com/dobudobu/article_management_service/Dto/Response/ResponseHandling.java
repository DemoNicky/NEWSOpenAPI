package com.dobudobu.article_management_service.Dto.Response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ResponseHandling<T> {

    private T data;

    private String status;

    private String message;

    private HttpStatus httpStatus;

    private ZonedDateTime timeStamp = ZonedDateTime.now();

    private Boolean errors;
}
