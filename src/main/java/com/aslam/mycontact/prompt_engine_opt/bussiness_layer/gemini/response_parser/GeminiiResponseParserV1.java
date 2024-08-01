package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Candidate;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.GeminiPayLoad;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Part;
import com.aslam.mycontact.prompt_engine_opt.exceptions.bussiness_layer.gemini.response_parser.GeminiParserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class GeminiiResponseParserV1 implements GeminiResponseParser {
     private final ObjectMapper objectMapper;


    public GeminiiResponseParserV1() {
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
         throw new GeminiParserException(e.getMessage(),responseBody);
     }
     return payLoad;
 }




}
