package com.aslam.mycontact.prompt_engine_opt.api_layer.prompt;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt.PromptPayload;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.PromptService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers.prompt_template.PromptTemplatePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/template")
public class PromptController {
 private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }
    @PostMapping("/prompt")
    ResponseEntity<PromptTemplatePayload> generatePrompt(@RequestBody String prompt)
    {
        return new ResponseEntity<PromptTemplatePayload>(
                promptService.sent(prompt),
                HttpStatus.CREATED);
    }


}
