package com.epam.gymappmainservice.api;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteTrainingRequest {
    @NotNull
    Integer trainingId;
    @NotNull
    private String token;
}
