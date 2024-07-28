package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Candidate;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.GeminiPayLoad;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Part;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JsonParserV1 {

    public Optional<List<Candidate>> getAllCandidates(GeminiPayLoad payLoad);
    public Optional<Candidate> getFirstCandidate(GeminiPayLoad payLoad);
    public GeminiPayLoad jsonToPayLoad(String responseBody);
    public Optional<Content> getContentByFirstCandidate(GeminiPayLoad payLoad);
    public Optional<Part>    getPartByContent(GeminiPayLoad payLoad);
}
