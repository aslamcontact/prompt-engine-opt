package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini;

public class GeminiApiException extends RuntimeException{

   static final String errorMessage="Gemini Api Error : ";
   private final String errorMessageClient="Please try again later";

    public GeminiApiException(String message) {
        super(errorMessage+message);

    }

    public GeminiApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorMessageClient() {
        return errorMessageClient;
    }
}
