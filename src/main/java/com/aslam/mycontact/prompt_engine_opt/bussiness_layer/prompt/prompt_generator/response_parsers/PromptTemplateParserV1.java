package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.prompt.prompt_generator.response_parsers.prompt_template.PromptTemplatePayload;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.prompt.prompt_generator.response_parsers.ResponseParserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.rsocket.server.RSocketServerException;
import org.springframework.stereotype.Component;

@Component
public class PromptTemplateParserV1 implements ResponseParser {
    private final ObjectMapper objectMapper;

    public PromptTemplateParserV1() {

        this.objectMapper = new ObjectMapper();
    }
   private String removeUnwanted(String responses)
   {
       String result;
       result=responses.replaceAll("```json","");
       result=result.replaceAll("```","");
       return result;
   }


    public PromptTemplatePayload parseJson(String response) {
        PromptTemplatePayload templates;
        String jsonResponse=this.removeUnwanted(response);
        try {

            templates = objectMapper.readValue(
                    jsonResponse,
                    PromptTemplatePayload.class);
        }catch (Exception e)
        {
            throw new ResponseParserException();
        }
        return templates;
    }



}
