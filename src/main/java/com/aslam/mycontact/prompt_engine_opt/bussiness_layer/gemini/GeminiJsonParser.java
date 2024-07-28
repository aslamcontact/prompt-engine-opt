package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Candidate;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.GeminiPayLoad;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Part;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class GeminiJsonParser implements JsonParserV1 {
     private final ObjectMapper objectMapper;


    public GeminiJsonParser() {
        this.objectMapper = new ObjectMapper();


        }


     public Optional<List<Candidate>> getAllCandidates(GeminiPayLoad payLoad)
     {

         return Optional.ofNullable( payLoad.getCandidates());
     }
     public Optional<Candidate> getFirstCandidate(GeminiPayLoad payLoad)
     {

         return Optional.of( payLoad.getCandidates().getFirst());
     }
     public Optional<Content> getContentByFirstCandidate(GeminiPayLoad payLoad)
     {
         return Optional.ofNullable(payLoad.getCandidates()
                 .getFirst()
                 .getContent());
     }

    @Override
    public Optional<Part> getPartByContent(GeminiPayLoad payLoad) {
        Optional<Content> content;

        content=this.getContentByFirstCandidate(payLoad);

        if (content.isEmpty()) return Optional.empty();

       return Optional.of(content.get().getParts().getFirst());
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
