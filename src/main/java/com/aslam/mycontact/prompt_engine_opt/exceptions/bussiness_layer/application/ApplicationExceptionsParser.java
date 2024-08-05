package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application;

import org.springframework.http.HttpStatus;

import java.util.PrimitiveIterator;

public class ApplicationExceptionsParser {
    private HttpStatus httpStatus;
    private String errorMessage;
    private String name;

    public ApplicationExceptionsParser(
                                       String errorMessage,
                                       String name,
                                       HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.name = name;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getName() {
        return name;
    }
}
