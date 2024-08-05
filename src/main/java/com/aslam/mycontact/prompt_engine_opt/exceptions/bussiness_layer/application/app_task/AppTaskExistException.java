package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.app_task;

public class AppTaskExistException extends RuntimeException{
    private final static String errorMessage="Task already exist.";
    private final String errorMessageClient="Task already exist.Please try other name.";
    private final String appName;
    private final String taskName;
    public AppTaskExistException(String appName,
                                 String taskName) {
        super(errorMessage);
        this.appName = appName;
        this.taskName=taskName;
    }

    public AppTaskExistException(Throwable cause,
                                 String appName,
                                 String  taskName) {
        super(errorMessage, cause);
        this.appName = appName;
        this.taskName=taskName;
    }

    public String getErrorMessageClient() {
        return errorMessageClient;
    }

    public String getAppName() {
        return appName;
    }

    public String getTaskName() {
        return taskName;
    }
}
