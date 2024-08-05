package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application;

public class AppExistException extends RuntimeException {
    private final static String errorMessage="App already exist.";
    private final String errorMessageClient="App already exist.Please try other name";
    private final String appName;



    public AppExistException(String appName) {
        super(errorMessage);
        this.appName = appName;
    }

    public AppExistException(String appName, Throwable cause) {
        super(errorMessage, cause);
        this.appName=appName;
    }

    public String getAppName() {
        return appName;
    }

    public String getErrorMessageClient() {
        return errorMessageClient;
    }
}
