package com.aslam.mycontact.prompt_engine_opt.bussiness_layer.application.app_task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppTaskPayload(
                             @NotNull(message ="taskName required" )
                             @NotBlank(message = "taskName can't be empty")
                             String taskName,
                             @NotNull(message ="taskTask required" )
                             @NotBlank(message = "taskTask can't be empty")
                             String aboutTask
                             ) {
}
