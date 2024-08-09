package com.aslam.mycontact.prompt_engine_opt.api_layer.prompt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record PromptTemplateWithParametersPayload(
        @NotBlank(message = "template can,t be empty")
        @NotNull(message = "template is required")
        String template,
        Map<String,String> parameters

) {
}
