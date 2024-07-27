package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.gemini.parser;

import java.util.List;


public class Content {
    private List<Part> parts;
    private String role;


    public Content(List<Part> parts, String role) {
        this.parts = parts;
        this.role = role;
    }

    public Content() {
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Content{" +
                "parts=" + parts +
                ", role='" + role + '\'' +
                '}';
    }
}
