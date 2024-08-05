package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppService;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTaskRepository;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.AppNotExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.app_task.AppTaskExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.app_task.AppTaskNotExistException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class AppTaskServiceV1 implements AppTaskService{
    private final AppService appService;
    private final AppTaskRepository appTaskRepository;

    public AppTaskServiceV1(AppService appService,
                            AppTaskRepository appTaskRepository) {
        this.appService = appService;
        this.appTaskRepository=appTaskRepository;
    }
    private App getApp(String appName)
    {
        return appService.getAppOrThrow(appName);
    }
    private Optional<AppTask> getTask(App app,String taskName)
    {
        return appTaskRepository.findTaskByTaskName(app,taskName);
    }

    @Override
    public AppTask createAppTask(App app,AppTaskPayload appTaskPayload) {
        var checkedApp=getApp(app.getAppName());
        var appTask=getTask(checkedApp,appTaskPayload.taskName());
        if(appTask.isPresent())
         throw new AppTaskExistException(checkedApp.getAppName(),appTask.get().getName());
        var newTask=new AppTask(
                appTaskPayload.taskName(),
                appTaskPayload.aboutTask(),
                checkedApp
        );
        return appTaskRepository.save(newTask);
    }

    @Override
    public Optional<AppTask> readAppTask(App app, String taskName) {
         var checkedApp=getApp(app.getAppName());
        return appTaskRepository.findTaskByTaskName(checkedApp,taskName);
    }

    @Override
    public Optional<AppTask> updateAppTask(App app, AppTaskPayload appTaskPayload) {
      var checkedApp=getApp(app.getAppName());
      var appTask=getTask(checkedApp,appTaskPayload.taskName());
      if(appTask.isEmpty())
        throw new AppTaskNotExistException(checkedApp.getAppName(),appTaskPayload.taskName());
      appTask.get().setAboutTask(appTaskPayload.aboutTask());
      appTask.get().setApplication(checkedApp);
      appTaskRepository.save(appTask.get());
        return appTask;
    }

    @Override
    public void deleteApp(App app, String taskName) {
        var checkedApp=getApp(app.getAppName());
        var appTask=getTask(checkedApp,taskName);
        if(appTask.isEmpty())
            throw new AppTaskNotExistException(checkedApp.getAppName(),taskName);
        appTask.ifPresent(appTaskRepository::delete);
    }

    @Override
    public Optional<List<AppTask>> readAllAppTask(App app) {
        var checkedApp=getApp(app.getAppName());
        List<AppTask> tasks=appTaskRepository.findTasksByApp(checkedApp);
        return Optional.of(tasks);
    }

    @Override
    public AppTask getTaskOrThrow(App app, String taskName) {
        var appTask=readAppTask(app,taskName);
        if(appTask.isEmpty())
            throw new AppTaskNotExistException(app.getAppName(),taskName);
        return appTask.get();
    }
}
