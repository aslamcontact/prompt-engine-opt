package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;


import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.GeminiPayLoad;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Part;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.GeminiResponseParser;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini.GeminiApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GeminiApiServiceV1 implements GeminiApiService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${app.ai.apikey}")
    private String apiKey;
    @Value("${app.ai.url}")
    private  String apiUrl;
    @Autowired
    private GeminiResponseParser parser;



    public GeminiPayLoad callApi(String prompt) {
        HttpEntity<Map<String, Object>> request;
        ResponseEntity<String> response;
        GeminiPayLoad payLoad;

         request = constructPayload(prompt);

          try {
              response = restTemplate.exchange(
                      apiUrl,
                      HttpMethod.POST,
                      request,
                      String.class);
             }catch (Exception e)
               {
                  throw new GeminiApiException(e.getMessage());

                 }

              payLoad = jsonToObjet(response.getBody());

   return payLoad;

    }

    private HttpEntity<Map<String,Object>> constructPayload(String prompt)
    {
        HttpEntity<Map<String, Object>> entity;
        ResponseEntity<String> response;
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> requestBody = new HashMap<>();

        List<Content> contents=new ArrayList<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key" ,apiKey);


        contents.add(
                new Content(
                        List.of(new Part(prompt)),
                        "user"));

        requestBody.put("contents", contents);
        entity = new HttpEntity<>(requestBody, headers);
        return entity;

    }

    private GeminiPayLoad jsonToObjet(String body)
    {

        return parser.jsonToPayLoad(body);

    }

}
