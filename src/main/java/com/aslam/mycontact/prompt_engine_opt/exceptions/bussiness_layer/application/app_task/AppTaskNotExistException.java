package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.app_task;

public class AppTaskNotExistException extends RuntimeException{
    private final static String errorMessage="Task not exist.";
    private final String errorMessageClient="Task not exist.Please try other name.";
    private final String appName;
    private final String taskName;
    public AppTaskNotExistException( String appName,
                                     String taskName) {
        super(errorMessage);
        this.appName = appName;
        this.taskName=taskName;
    }

    public AppTaskNotExistException( Throwable cause,
                                     String appName,
                                     String  taskName) {
        super(errorMessage, cause);
        this.appName = appName;
        this.taskName=taskName;
    }

    public String getAppName() {
        return appName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getErrorMessageClient() {
        return errorMessageClient;
    }
}
