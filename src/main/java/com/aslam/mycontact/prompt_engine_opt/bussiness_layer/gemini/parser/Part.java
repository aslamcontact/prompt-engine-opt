package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser;

public class Part {
    private String text;

    public Part() {
    }

    public Part(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Part{" +
                "text='" + text + '\'' +
                '}';
    }
}
