package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application;

public class AppNotExistException extends RuntimeException{
    private final static String errorMessage="App not exist.";
    private final String errorMessageClient="App not exist.please check the app name.";
    private final String appName;


    public AppNotExistException(String appName) {
        super(errorMessage);
        this.appName = appName;
    }

    public AppNotExistException(Throwable cause, String appName) {
        super(errorMessage, cause);
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public String getErrorMessageClient() {
        return errorMessageClient;
    }
}
