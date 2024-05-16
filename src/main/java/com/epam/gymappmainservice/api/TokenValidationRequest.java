package com.epam.gymappmainservice.api;

import lombok.Data;

@Data
public class TokenValidationRequest {
    private String token;

    public TokenValidationRequest(String token) {
        this.token = token;
    }

    public TokenValidationRequest() {}
}
