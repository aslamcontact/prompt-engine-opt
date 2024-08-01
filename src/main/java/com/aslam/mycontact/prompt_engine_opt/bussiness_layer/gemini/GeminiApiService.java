package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;


import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.response_parser.gemini_payload.GeminiPayLoad;
import org.springframework.stereotype.Service;

@Service
public interface GeminiApiService {

    public GeminiPayLoad callApi(String prompt) ;

}
