package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini;


import com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser.GeminiPayLoad;
import org.springframework.stereotype.Service;

@Service
public interface GeminiApiServiceV1 {

    public GeminiPayLoad callApi(String prompt) ;

}
