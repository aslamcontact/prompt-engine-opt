package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskPayload;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt.Prompt;

import java.util.List;
import java.util.Optional;

public interface AppPromptPayLoadParser {
    AppPromptPayload parse(Prompt prompt);
    Optional<List<AppPromptPayload>> parse(Optional<List<Prompt>> prompts);

}
