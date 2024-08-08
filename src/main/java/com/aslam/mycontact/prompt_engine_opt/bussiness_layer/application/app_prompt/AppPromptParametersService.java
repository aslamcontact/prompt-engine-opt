package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface AppPromptParametersService {
    List<String> extractParameters(String promptTemplate);
    String replaceParameters(String promptTemplate, Map<String,String> parametersAndValues);
}
