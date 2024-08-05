package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AppService {

    App createApp(AppPayload appPayload);
    Optional<App> readApp(String appName);
    Optional<App> updateApp(AppPayload appPayload);
    void deleteApp(String appName);
    Optional<List<App>> readAllApp();
    App getAppOrThrow(String appName);



}
