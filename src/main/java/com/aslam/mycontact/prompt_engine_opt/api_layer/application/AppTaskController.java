package com.aslam.mycontact.prompt_engine_opt.api_layer.application;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskPayloadParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task.AppTaskService;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.app_task.AppTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/application/apptask/{appName}")
public class AppTaskController {
    private final AppService appService;
    private final AppTaskService appTaskService;
    private final AppTaskPayloadParser parser;

    public AppTaskController(AppTaskService appTaskService,
                             AppTaskPayloadParser parser,
                             AppService appService) {
        this.appTaskService = appTaskService;
        this.parser = parser;
        this.appService=appService;
    }
    private App checkApp(String appName)
    {
        return appService.getAppOrThrow(appName);
    }
    @GetMapping
    ResponseEntity<Optional<List<AppTaskPayload>>> gesApps(
            @PathVariable(value = "appName") String appName)
    {
        var app=checkApp(appName);
        Optional<List<AppTask>> apps=appTaskService.readAllAppTask(app);
        return new ResponseEntity<>(parser.parse(apps), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<AppTaskPayload> createAppTask(
            @PathVariable(value = "appName") String appName,
            @RequestBody AppTaskPayload appTaskPayload)
    {
        var app=checkApp(appName);
        var appTask=appTaskService.createAppTask(app,appTaskPayload);
        return new ResponseEntity<>(parser.parse(appTask),HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<AppTaskPayload> updateAppTask(
            @PathVariable(value = "appName") String appName,
            @RequestBody AppTaskPayload appTaskPayload)
    {
        var app=checkApp(appName);
        var appTask=appTaskService.createAppTask(app,appTaskPayload);
        AppTask updatedAppTask=appTaskService.updateAppTask(app,appTaskPayload).get();
        return new ResponseEntity<>(parser.parse(updatedAppTask),HttpStatus.CREATED);
    }
    @GetMapping("/{taskName}")
    ResponseEntity<AppTaskPayload> getAppTask(@PathVariable(value = "appName") String appName,
                                          @PathVariable(value = "taskName") String taskName)
    {
        var app=checkApp(appName);
        var appTask=appTaskService.readAppTask(app,taskName).get();
        return new ResponseEntity<>(parser.parse(appTask),HttpStatus.OK);
    }
    @DeleteMapping("/{taskName}")
    ResponseEntity<String> removeAppTask(@PathVariable(value = "appName") String appName,
                                     @PathVariable(value = "taskName") String taskName)
    {
        var app=checkApp(appName);
        appTaskService.deleteApp(app,taskName);
        return new ResponseEntity<>(taskName,HttpStatus.OK);
    }



}
