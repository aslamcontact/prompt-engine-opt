package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini;

import org.springframework.http.HttpStatus;

public class GeminiApiExceptionParsers {
    private final String errorMessage;
    private final HttpStatus httpStatus;

    public GeminiApiExceptionParsers(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
