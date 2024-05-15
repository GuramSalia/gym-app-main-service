package com.epam.gymappmainservice.api;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FullStatRequest {
    @NotNull
    private Integer trainerId;
    @NotNull
    private String token;
}
