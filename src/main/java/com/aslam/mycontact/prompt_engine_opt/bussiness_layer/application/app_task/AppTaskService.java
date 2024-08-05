package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayload;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AppTaskService {
    AppTask createAppTask(App app,AppTaskPayload appTaskPayload);
    Optional<AppTask> readAppTask(App app,String taskName);
    Optional<AppTask> updateAppTask(App app,AppTaskPayload appTaskPayload);
    void deleteApp(App app,String taskName);
    Optional<List<AppTask>> readAllAppTask(App app);
    AppTask getTaskOrThrow(App app,String taskName);

}
