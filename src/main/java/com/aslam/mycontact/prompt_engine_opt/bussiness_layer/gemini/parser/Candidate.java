package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candidate {
    private Content content;
    private String finishReason;
    private Integer index;


    public Candidate() {
    }

    public Candidate(Content content) {
        this.content = content;
    }



    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    @Override
    public String toString() {
        return "Candidate{" +
                "content=" + content +
                ", finishReason='" + finishReason + '\'' +
                ", index=" + index +
                '}';
    }
}
