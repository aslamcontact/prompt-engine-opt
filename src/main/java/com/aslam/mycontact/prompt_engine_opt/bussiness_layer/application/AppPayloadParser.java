package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application;

import com.aslam.mycontact.prompt_engine_opt.dao_layer.application.App;

import java.util.List;
import java.util.Optional;

public interface AppPayloadParser {
    AppPayload parse(App app);
    Optional<List<AppPayload>> parse(Optional<List<App>> apps);
}
