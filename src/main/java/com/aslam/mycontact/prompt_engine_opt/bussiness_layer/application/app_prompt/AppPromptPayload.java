package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_prompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppPromptPayload(
        @NotNull(message ="promptName required" )
        @NotBlank(message = "promptName can't be empty")
        String promptName,
        @NotNull(message ="template required" )
        @NotBlank(message = "template can't be empty")
        String template,
        @NotNull(message ="examplePrompt required" )
        @NotBlank(message = "examplePrompt can't be empty")
        String examplePrompt,
        @NotNull(message ="outputFormat required" )
        @NotBlank(message = "outputFormat can't be empty")
        String outputFormat) {
}
