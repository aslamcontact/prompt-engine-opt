package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.prompt.prompt_generator.response_parsers;

public class ResponseParserException extends RuntimeException{
    static  final String errorMessage="Error while parsing";

    public ResponseParserException() {
        super(errorMessage);
    }

    public ResponseParserException(Throwable cause) {
        super(errorMessage,cause);
    }

    public String getErrorClientSide() {
        return "Please try other prompt";
    }
}
