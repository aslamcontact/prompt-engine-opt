package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiPayLoad {
    @JsonProperty("candidates")
    private List<Candidate> candidates;

    @JsonProperty("usageMetadata")
    private UsageMetadata usageMetadata;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public UsageMetadata getUsageMetadata() {
        return usageMetadata;
    }

    public void setUsageMetadata(UsageMetadata usageMetadata) {
        this.usageMetadata = usageMetadata;
    }


}
