package com.epam.gymappmainservice.exception;

public class UserNotCreatedException extends RuntimeException{
    public UserNotCreatedException(String message) {
        super(message);
    }
}
