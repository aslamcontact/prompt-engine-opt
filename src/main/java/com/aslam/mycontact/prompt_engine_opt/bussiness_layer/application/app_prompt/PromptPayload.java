package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

public record PromptPayload(String promptName,
                            String template,
                            String examplePrompt,
                            String outputFormat) {
}
