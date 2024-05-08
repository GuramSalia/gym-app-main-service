package com.epam.gymappmainservice.utils;

import com.epam.gymappmainservice.model.User;

import java.util.Optional;

public interface ImplementsFindByUsernameAndPassword {
    public <T extends User> Optional<T> findByUsernameAndPassword(String username, String password);

    public <T extends User> Optional<T> findByUsername(String username);
}
