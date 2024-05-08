package com.epam.gymappmainservice.service;

import com.epam.gymappmainservice.model.Token;
import com.epam.gymappmainservice.model.User;
import com.epam.gymappmainservice.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Token findByToken(String token) {
        return tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found"));
    }

    public void delete(String token) {
        tokenRepository.deleteByToken(token);
    }

    public List<Token> findByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    public List<Token> findByUsername(String username) {
        return tokenRepository.findByUserUsername(username);
    }
}
