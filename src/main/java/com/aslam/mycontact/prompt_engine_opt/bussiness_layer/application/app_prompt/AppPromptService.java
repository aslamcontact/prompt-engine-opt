package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AppPromptService {
    Prompt createPrompt(App app, AppTask appTask, AppPromptPayload appPromptPayload);
    Optional<Prompt> readPrompt(App app, AppTask appTask, String promptName);
    Optional<Prompt> updatePrompt(App app, AppTask appTask, AppPromptPayload appPromptPayload);
    void deletePrompt(App app, AppTask appTask,String promptName);
    Optional<List<Prompt>> readAllPrompt(App app, AppTask appTask);
    Prompt getPromptOrThrow(App app,AppTask appTask,String promptName);

}
