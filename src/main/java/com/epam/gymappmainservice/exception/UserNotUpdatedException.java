package com.epam.gymappmainservice.exception;

public class UserNotUpdatedException extends RuntimeException{
    public UserNotUpdatedException(String message) {
        super(message);
    }
}