package com.aslam.mycontact.prompt_engine_opt.api_layer.prompt;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt.AppPromptParametersService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.GeminiApiService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.GeminiResponseParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Part;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.PromptService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers.prompt_template.PromptTemplatePayload;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prompt")
public class PromptController {
    private final PromptService promptService;
    private final AppPromptParametersService appPromptParametersService;
    private final GeminiApiService geminiApiService;
    private final GeminiResponseParser geminiResponseParser;

    public PromptController( PromptService promptService,
                             AppPromptParametersService appPromptParametersService,
                             GeminiApiService geminiApiService,
                             GeminiResponseParser geminiResponseParser) {
        this.promptService = promptService;
        this.appPromptParametersService=appPromptParametersService;
        this.geminiApiService=geminiApiService;
        this.geminiResponseParser=geminiResponseParser;
    }
    @GetMapping("/template/generate")
    ResponseEntity<PromptTemplatePayload> generatePrompt(@RequestBody String myProblem)
    {
        return new ResponseEntity<PromptTemplatePayload>(
                promptService.sent(myProblem),
                HttpStatus.OK);
    }
    @GetMapping("/template/parameters")
    ResponseEntity<Optional<List<String>>> getTemplateParameter(@RequestBody String promptTemplate)
    {
        List<String> parameters;
        parameters=appPromptParametersService.extractParameters(promptTemplate);
        return new ResponseEntity<Optional<List<String>>>(
                Optional.of(parameters),
                HttpStatus.OK);
    }

    @GetMapping("/template/call")
    ResponseEntity<String> callLlmWithTemplate(
            @Valid @RequestBody PromptTemplateWithParametersPayload payLoad)
    {
        String finalPrompt;
        String result;
        Optional<Part> geminiResult;
        finalPrompt=appPromptParametersService.replaceParameters(payLoad.template(),
                payLoad.parameters());
        geminiResult= geminiResponseParser.getPartByContent(geminiApiService.callApi(finalPrompt));
       result=geminiResult.isEmpty()?"Please try other prompt":geminiResult.get().getText();
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

}
