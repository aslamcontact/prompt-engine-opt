package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Candidate;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.GeminiPayLoad;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GeminiJsonParser implements JsonParserV1 {
     private final ObjectMapper objectMapper;


    public GeminiJsonParser() {
        this.objectMapper = new ObjectMapper();


        }


     public List<Candidate> getAllCandidates(GeminiPayLoad payLoad)
     {
         return payLoad.getCandidates();
     }
     public Candidate getFirstCandidate(GeminiPayLoad payLoad)
     {
         return payLoad.getCandidates().getFirst();
     }
     public Content getContentByFirstCandidate(GeminiPayLoad payLoad)
     {
         return payLoad.getCandidates()
                 .getFirst()
                 .getContent();
     }


 public GeminiPayLoad jsonToPayLoad(String responseBody)
 {
     GeminiPayLoad payLoad;
     try {
        payLoad=  objectMapper
                 .readValue(responseBody, GeminiPayLoad.class);
     }catch (Exception e)
     {
         throw new IllegalStateException("parser "+e.getMessage());
     }
     return payLoad;
 }




}
