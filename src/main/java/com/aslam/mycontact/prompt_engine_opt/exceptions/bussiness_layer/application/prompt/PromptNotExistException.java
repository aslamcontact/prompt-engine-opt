package com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.prompt;

public class PromptNotExistException extends RuntimeException{
    private final static String errorMessage="Prompt not exist.";
    private final String errorMessageClient="Prompt not exist.Please try correct prompt name.";
    private final String appName;
    private final String taskName;
    private final String promptName;
    public PromptNotExistException( String appName,
                                    String taskName,
                                    String promptName) {
        super(errorMessage);
        this.appName = appName;
        this.taskName=taskName;
        this.promptName=promptName;
    }

    public PromptNotExistException( Throwable cause,
                                    String appName,
                                    String  taskName,
                                    String promptName) {
        super(errorMessage, cause);
        this.appName = appName;
        this.taskName=taskName;
        this.promptName=promptName;
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

    public String getPromptName() {
        return promptName;
    }
}
