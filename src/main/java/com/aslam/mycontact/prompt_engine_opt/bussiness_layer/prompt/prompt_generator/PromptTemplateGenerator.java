package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.GeminiApiService;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.GeminiResponseParser;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.GeminiPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class PromptTemplateGenerator {
 abstract public String sent(String prompt);

 @Autowired
 private GeminiApiService gemini;
 @Autowired
 private GeminiResponseParser parser;

    protected String callLlm(String prompt)
    {
        return parsePayload(gemini.callApi(prompt));
    }
    private String parsePayload(GeminiPayLoad payLoad)
    {

        if(parser.getPartByContent(payLoad).isEmpty())
            return "Try another Prompt";

        return parser.getPartByContent(payLoad).get().getText();
    }


}
