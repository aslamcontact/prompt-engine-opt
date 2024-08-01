package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.PromptTemplateGenerator;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers.ResponseParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers.prompt_template.PromptTemplatePayload;
import org.springframework.stereotype.Component;

@Component
public class PromptServiceV1 implements PromptService{
    private final PromptTemplateGenerator promptTemplateGenerator;
    private final ResponseParser responseParser;

    public PromptServiceV1(
            PromptTemplateGenerator promptTemplateGenerator,
            ResponseParser responseParser) {
        this.promptTemplateGenerator = promptTemplateGenerator;
        this.responseParser=responseParser;
    }

    @Override
    public PromptTemplatePayload sent(String prompt) {


            String response = promptTemplateGenerator.sent(prompt);
            return responseParser.parseJson(response);


    }
}
