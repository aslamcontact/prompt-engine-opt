package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.AppRepository;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.AppExistException;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.application.AppNotExistException;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
@Component
public class AppServiceV1 implements AppService{
    private final AppRepository appRepository;

    public AppServiceV1(AppRepository appRepository) {
        this.appRepository = appRepository;
    }
    private Optional<App> getApp(String appName)
    {
        return appRepository.findByAppName(appName);
    }
    @Override
    public App createApp(AppPayload appPayload) {
        var app =getApp(appPayload.appName());
        if(app.isPresent())
            throw new AppExistException(app.get().getAppName());
         var newApp=new App(appPayload.appName(),appPayload.description());
       return appRepository.save(newApp);
    }

    @Override
    public Optional<App> readApp(String appName) {
        return appRepository.findByAppName(appName);
    }

    @Override
    public Optional<App> updateApp(AppPayload appPayload) {
        var app=getApp(appPayload.appName());
        if(app.isEmpty())
            throw new AppNotExistException(appPayload.appName());
        app.get().setDescriptions(appPayload.description());
        appRepository.save(app.get());
        return app;
    }

    @Override
    public void deleteApp(String appName) {
        var app=getApp(appName);
        if (app.isEmpty())
            throw new AppNotExistException(appName);
        app.ifPresent(appRepository::delete);
     }
    @Override
    public Optional<List<App>> readAllApp() {
        List<App> apps=appRepository.findAll();
        return Optional.of(apps);
    }

    @Override
    public App getAppOrThrow(String appName) {
        var app=readApp(appName);
        if(app.isEmpty())
            throw new AppNotExistException(appName);
        return app.get();
    }
}
