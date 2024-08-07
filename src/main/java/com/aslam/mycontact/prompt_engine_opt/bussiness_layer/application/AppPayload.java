package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AppPayload(
        @NotBlank(message = "appName can't be empty")
        @NotNull(message = "appNane required")
        String appName,
        @NotBlank(message = "description can't be empty")
        @NotNull(message = "description required")
        String description) {
}
