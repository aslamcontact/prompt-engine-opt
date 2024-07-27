package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser;

public class UsageMetadata {
    private Integer promptTokenCount;
   private Integer candidatesTokenCount;
   private Integer totalTokenCount;


    public UsageMetadata() {
    }

    public Integer getPromptTokenCount() {
        return promptTokenCount;
    }

    public void setPromptTokenCount(Integer promptTokenCount) {
        this.promptTokenCount = promptTokenCount;
    }

    public Integer getCandidatesTokenCount() {
        return candidatesTokenCount;
    }

    public void setCandidatesTokenCount(Integer candidatesTokenCount) {
        this.candidatesTokenCount = candidatesTokenCount;
    }

    public Integer getTotalTokenCount() {
        return totalTokenCount;
    }

    public void setTotalTokenCount(Integer totalTokenCount) {
        this.totalTokenCount = totalTokenCount;
    }
}
