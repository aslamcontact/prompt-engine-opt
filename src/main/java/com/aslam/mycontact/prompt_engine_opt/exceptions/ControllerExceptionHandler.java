package com.aslam.mycontact.prompt_engine_opt.exceptions;

import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.AppExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.AppNotExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.ApplicationExceptionsParser;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.app_task.AppTaskExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.app_task.AppTaskNotExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.prompt.PromptExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.prompt.PromptNotExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini.GeminiApiException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini.GeminiApiExceptionParsers;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini.response_parser.GeminiParserException;
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
  @ExceptionHandler
    public  ResponseEntity<Object> GeminiApiException(GeminiApiException exception)
    {
        HttpStatus httpStatus=HttpStatus.FOUND;
        GeminiApiExceptionParsers parsers=new GeminiApiExceptionParsers(
                exception.getErrorMessageClient(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }

    @ExceptionHandler
    public  ResponseEntity<Object> GeminiParserException(GeminiParserException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        GeminiApiExceptionParsers parsers=new GeminiApiExceptionParsers(
                exception.getErrorMessageClient(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }
    @ExceptionHandler
    public  ResponseEntity<Object> appExistException(AppExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApplicationExceptionsParser parsers;
        parsers=new ApplicationExceptionsParser(
                exception.getErrorMessageClient(),
                exception.getAppName(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }
    @ExceptionHandler
    public  ResponseEntity<Object> appNotExistException(AppNotExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApplicationExceptionsParser parsers;
        parsers=new ApplicationExceptionsParser(
                exception.getErrorMessageClient(),
                exception.getAppName(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }
    @ExceptionHandler
    public  ResponseEntity<Object> appTaskExistException(AppTaskExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApplicationExceptionsParser parsers;
        parsers=new ApplicationExceptionsParser(
                exception.getErrorMessageClient(),
                exception.getTaskName(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }
    @ExceptionHandler
    public  ResponseEntity<Object> appTaskNotExistException(AppTaskNotExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApplicationExceptionsParser parsers;
        parsers=new ApplicationExceptionsParser(
                exception.getErrorMessageClient(),
                exception.getAppName(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }

    @ExceptionHandler
    public  ResponseEntity<Object> appPromptExistException(PromptExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApplicationExceptionsParser parsers;
        parsers=new ApplicationExceptionsParser(
                exception.getErrorMessageClient(),
                exception.getPromptName(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }
    @ExceptionHandler
    public  ResponseEntity<Object> appPromptNotExistException(PromptNotExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        ApplicationExceptionsParser parsers;
        parsers=new ApplicationExceptionsParser(
                exception.getErrorMessageClient(),
                exception.getPromptName(),
                httpStatus);
        return new ResponseEntity<>(parsers,httpStatus);
    }

}
