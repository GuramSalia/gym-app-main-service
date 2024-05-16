package com.epam.gymappmainservice.api;

import lombok.Data;

@Data
public class TokenValidationResponse {
    private boolean tokenIsValid;

    public TokenValidationResponse(boolean tokenIsValid) {
        this.tokenIsValid = tokenIsValid;
    }

    public TokenValidationResponse() {}
}
