package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini.response_parser;

public class GeminiParserException extends RuntimeException{
    static final String errorMessage="Problem While parse Gemini Api Response : ";
    private String responseBody;
    private final  String errorMessageClient="We can't process your request,Please try other prompt";
    public GeminiParserException(String message,String responseBody) {
        super(errorMessage+message);
        this.responseBody=responseBody;
    }

    public GeminiParserException(String message,String responseBody, Throwable cause) {
        super(errorMessage+message, cause);
        this.responseBody=responseBody;
    }

    public String getErrorMessageClient() {
        return errorMessageClient;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
