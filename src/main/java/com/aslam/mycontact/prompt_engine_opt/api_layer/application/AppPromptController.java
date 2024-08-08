package com.aslam.mycontact.prompt_engine_opt.api_layer.application;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt.AppPromptPayLoadParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt.AppPromptPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt.AppPromptService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskPayloadParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskService;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.prompt.Prompt;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/application/prompt/{appName}/{taskName}")
public class AppPromptController {
    private final AppService appService;
    private final AppTaskService appTaskService;
    private final AppPromptService appPromptService;
    private final AppPromptPayLoadParser parser;

    public AppPromptController(AppService appService,
                               AppTaskService appTaskService,
                               AppPromptService appPromptService,
                               AppPromptPayLoadParser parser) {
        this.appService = appService;
        this.appTaskService = appTaskService;
        this.appPromptService = appPromptService;
        this.parser = parser;
    }
    private App checkApp(String appName)
    {
        return appService.getAppOrThrow(appName);
    }
    private AppTask checkTask(App app,String taskName)
    {
        return appTaskService.getTaskOrThrow(app,taskName);
    }

    @GetMapping
    ResponseEntity<Optional<List<AppPromptPayload>>> gesPrompts(
            @PathVariable(value = "appName") String appName,
            @PathVariable(value = "taskName") String taskName)
    {
        var app=checkApp(appName);
        var appTask=checkTask(app,taskName);
        Optional<List<Prompt>> prompts=appPromptService.readAllPrompt(app,appTask);
        return new ResponseEntity<>(parser.parse(prompts), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<AppPromptPayload> createPrompt(
            @PathVariable(value = "appName") String appName,
            @PathVariable(value = "taskName") String taskName,
            @Valid @RequestBody  AppPromptPayload appPromptPayload)
    {
        var app=checkApp(appName);
        var appTask=checkTask(app,taskName);
        var appPrompt=appPromptService.createPrompt(app,appTask,appPromptPayload);
        return new ResponseEntity<>(parser.parse(appPrompt),HttpStatus.CREATED);
    }
    @PutMapping
    ResponseEntity<AppPromptPayload> updatePrompt(
            @PathVariable(value = "appName") String appName,
            @PathVariable(value = "taskName") String taskName,
            @Valid @RequestBody AppPromptPayload appPromptPayload)
    {
        var app=checkApp(appName);
        var appTask=checkTask(app,taskName);
        var appPrompt=appPromptService.updatePrompt(app,appTask,appPromptPayload).get();
        return new ResponseEntity<>(parser.parse(appPrompt),HttpStatus.CREATED);
    }
    @GetMapping("/{promptName}")
    ResponseEntity<AppPromptPayload> getPrompt(
            @PathVariable(value = "appName") String appName,
            @PathVariable(value = "taskName") String taskName,
            @PathVariable(value = "promptName") String promptName)
    {
        var app=checkApp(appName);
        var appTask=checkTask(app,taskName);
        var appPrompt=appPromptService.readPrompt(app,appTask,promptName).get();
        return new ResponseEntity<>(parser.parse(appPrompt),HttpStatus.OK);
    }
    @DeleteMapping("/{promptName}")
    ResponseEntity<String> removePrompt(
            @PathVariable(value = "appName") String appName,
            @PathVariable(value = "taskName") String taskName,
            @PathVariable(value = "promptName") String promptName)
    {
        var app=checkApp(appName);
        var appTask=checkTask(app,taskName);
        appPromptService.deletePrompt(app,appTask,promptName);
        return new ResponseEntity<>(promptName,HttpStatus.OK);
    }
    @GetMapping("/parameters/{promptName}")
    ResponseEntity<Optional<List<String>>> getParameters(
            @PathVariable(value = "appName") String appName,
            @PathVariable(value = "taskName") String taskName,
            @PathVariable(value = "promptName") String promptName)
    {
        Optional<List<String>> parameters;
        var app=checkApp(appName);
        var appTask=checkTask(app,taskName);
        var appPrompt=appPromptService.getPromptOrThrow(app,appTask,promptName);
        parameters=Optional.of(appPrompt.getTemplateParameters());
        return new ResponseEntity<>(parameters,HttpStatus.OK);
    }



}
