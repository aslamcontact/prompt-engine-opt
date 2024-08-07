package com.aslam.mycontact.prompt_engine_opt.api_layer.application;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppPayloadParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.AppService;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/application/app")
public class AppController {
 private final AppService appService;
 private  final AppPayloadParser parser;

    public AppController(AppService appService,
                         AppPayloadParser parser) {
        this.appService = appService;
        this.parser=parser;
    }

    @GetMapping
    ResponseEntity<Optional<List<AppPayload>>> gesApps()
    {
        Optional<List<App>> apps=appService.readAllApp();
        return new ResponseEntity<>(parser.parse(apps),HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<AppPayload> createApp(@Valid @RequestBody AppPayload appPayload)
    {
        App app=appService.createApp(appPayload);
        return new ResponseEntity<>(parser.parse(app),HttpStatus.CREATED);
    }
    @PutMapping
    ResponseEntity<AppPayload> updateApp(@Valid @RequestBody AppPayload appPayload)
    {
        App updatedApp=appService.updateApp(appPayload).get();
        return new ResponseEntity<>(parser.parse(updatedApp),HttpStatus.CREATED);
    }
    @GetMapping("/{appName}")
    ResponseEntity<AppPayload> getApp(@PathVariable(value = "appName") String appName)
    {
        App app=appService.getAppOrThrow(appName);
        return new ResponseEntity<>(parser.parse(app),HttpStatus.OK);
    }
    @DeleteMapping("/{appName}")
    ResponseEntity<String> removeApp(@PathVariable(value = "appName") String appName)
    {
        appService.deleteApp(appName);
        return new ResponseEntity<>(appName,HttpStatus.OK);
    }

}
