package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayloadParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskService;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt.Prompt;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt.PromptRepository;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.AppNotExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.prompt.PromptExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.prompt.PromptNotExistException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AppPromptServiceV1 implements AppPromptService, AppPromptPayLoadParser {
private final AppService appService;
private final AppTaskService appTaskService;
private final PromptRepository promptRepository;
private final AppPromptParametersService appPromptParametersService;

    public AppPromptServiceV1(AppService appService,
                              AppTaskService appTaskService,
                              PromptRepository promptRepository,
                              AppPromptParametersService appPromptParametersService) {
        this.appService = appService;
        this.appTaskService = appTaskService;
        this.promptRepository=promptRepository;
        this.appPromptParametersService=appPromptParametersService;
    }

    private App getApp(App app)
    {
       return appService.getAppOrThrow(app.getAppName());
    }
    private AppTask getTask(  App app,
                              AppTask appTask)
    {
        return appTaskService.getTaskOrThrow(app,appTask.getName());
    }
    private Optional<Prompt> getPrompt(AppTask appTask,
                                       String promptName)
    {
        return promptRepository.findPromptByPromptName(appTask,promptName);
    }
    @Override
    public Prompt createPrompt(App app,
                               AppTask appTask,
                               AppPromptPayload appPromptPayload) {
        var checkedApp=getApp(app);
        var checkedAppTask=getTask(checkedApp,appTask);
        var prompt=getPrompt(checkedAppTask, appPromptPayload.promptName());
        if(prompt.isPresent())
            throw new PromptExistException( checkedApp.getAppName(),
                                            checkedAppTask.getName(),
                                            prompt.get().getPromptName());
        var newPrompt=new Prompt(
                appPromptPayload.promptName(),
                appPromptPayload.template(),
                appPromptParametersService.extractParameters(appPromptPayload.template()),
                appPromptPayload.examplePrompt(),
                appPromptPayload.outputFormat(),
                checkedAppTask);
        return promptRepository.save(newPrompt);

    }

    @Override
    public Optional<Prompt> readPrompt(App app,
                                       AppTask appTask,
                                       String promptName) {
        var checkedApp=getApp(app);
        var checkedAppTask=getTask(checkedApp,appTask);

        return promptRepository.findPromptByPromptName(checkedAppTask,promptName);
    }

    @Override
    public Optional<Prompt> updatePrompt(App app,
                                         AppTask appTask,
                                         AppPromptPayload appPromptPayload) {
        var checkedApp=getApp(app);
        var checkedAppTask=getTask(checkedApp,appTask);

        var prompt=getPrompt(checkedAppTask, appPromptPayload.promptName());
        if(prompt.isEmpty())
            throw new PromptNotExistException(
                    checkedApp.getAppName(),
                    checkedAppTask.getName(),
                    appPromptPayload.promptName());
        var newPrompt=prompt.get();
        newPrompt.setPromptName(appPromptPayload.promptName());
        newPrompt.setExamplePrompt(appPromptPayload.examplePrompt());
        newPrompt.setTemplate(appPromptPayload.template());
        newPrompt.setTemplateParameters(appPromptParametersService.extractParameters(appPromptPayload.template()));
        newPrompt.setOutputFormat(appPromptPayload.outputFormat());
        return Optional.of(promptRepository.save(newPrompt));
    }

    @Override
    public void deletePrompt(App app,
                             AppTask appTask,
                             String promptName) {
        var checkedApp=getApp(app);
        var checkedAppTask=getTask(checkedApp,appTask);

        var prompt=getPrompt(checkedAppTask,promptName);
        if(prompt.isEmpty())
            throw new PromptNotExistException(
                    checkedApp.getAppName(),
                    checkedAppTask.getName(),
                    promptName);
        prompt.ifPresent(promptRepository::delete);

    }

    @Override
    public Optional<List<Prompt>> readAllPrompt(App app,
                                                AppTask appTask) {
        var checkedApp=getApp(app);
        var checkedAppTask=getTask(checkedApp,appTask);
        List<Prompt> prompts=promptRepository.findPromptsByAppTask(checkedAppTask);
        return Optional.of(prompts);
    }

    @Override
    public Prompt getPromptOrThrow(App app, AppTask appTask, String promptName) {

        var prompt=readPrompt(app,appTask,promptName);
        if(prompt.isEmpty())
            throw new PromptNotExistException(promptName,
                    appTask.getName(),
                    promptName);
        return prompt.get();
    }


    @Override
    public AppPromptPayload parse(Prompt prompt) {
        return new AppPromptPayload(prompt.getPromptName(),
                prompt.getTemplate(),
                prompt.getExamplePrompt(),
                prompt.getOutputFormat());
    }

    @Override
    public Optional<List<AppPromptPayload>> parse(Optional<List<Prompt>> prompts) {
        List<AppPromptPayload> appPayloads=new ArrayList<AppPromptPayload>();
        if(prompts.isEmpty())
            return Optional.empty();
        prompts.get()
                .stream()
                .forEach(a->appPayloads.add(parse(a)));
        return Optional.of(appPayloads);

    }


}
