package com.epam.gymappmainservice.exception;

public class UserNotAuthenticatedException extends RuntimeException{
    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
