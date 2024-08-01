package com.aslam.mycontact.prompt_engine_opt.exceptions;

import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.prompt.PromptExceptionsParser;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.prompt.prompt_generator.response_parsers.ResponseParserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> responseParserException(ResponseParserException exception) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        PromptExceptionsParser parser=new PromptExceptionsParser(
                exception.getErrorClientSide(),
                httpStatus);
        return new ResponseEntity<>(parser, httpStatus);
    }

}
