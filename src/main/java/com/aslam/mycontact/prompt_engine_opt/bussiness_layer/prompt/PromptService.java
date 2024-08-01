package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers.prompt_template.PromptTemplatePayload;
import org.springframework.stereotype.Service;

@Service
public interface PromptService {
    public PromptTemplatePayload sent(String prompt);
}
