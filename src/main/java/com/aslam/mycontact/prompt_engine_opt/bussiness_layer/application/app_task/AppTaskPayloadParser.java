package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayload;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;

import java.util.List;
import java.util.Optional;

public interface AppTaskPayloadParser {
    AppTaskPayload parse(AppTask appTask);
    Optional<List<AppTaskPayload>> parse(Optional<List<AppTask>> appTasks);


}
