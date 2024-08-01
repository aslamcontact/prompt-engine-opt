package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Candidate;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.GeminiPayLoad;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.Part;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GeminiResponseParser {

    public Optional<List<Candidate>> getAllCandidates(GeminiPayLoad payLoad);
    public Optional<Candidate> getFirstCandidate(GeminiPayLoad payLoad);
    public GeminiPayLoad jsonToPayLoad(String responseBody);
    public Optional<Content> getContentByFirstCandidate(GeminiPayLoad payLoad);
    public Optional<Part>    getPartByContent(GeminiPayLoad payLoad);
}
