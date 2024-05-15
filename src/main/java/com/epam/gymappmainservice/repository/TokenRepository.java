package com.epam.gymappmainservice.repository;

import com.epam.gymappmainservice.model.Token;
import com.epam.gymappmainservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);

    void deleteByToken(String token);

    List<Token> findByUser(User user);

//    List<Token> findByUsername(String username);
}
