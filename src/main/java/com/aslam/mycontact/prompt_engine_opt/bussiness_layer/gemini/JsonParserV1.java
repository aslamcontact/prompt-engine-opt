package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;

import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Candidate;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.Content;
import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.GeminiPayLoad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JsonParserV1 {

    public List<Candidate> getAllCandidates(GeminiPayLoad payLoad);
    public Candidate getFirstCandidate(GeminiPayLoad payLoad);
    public GeminiPayLoad jsonToPayLoad(String responseBody);
    public Content getContentByFirstCandidate(GeminiPayLoad payLoad);
}
