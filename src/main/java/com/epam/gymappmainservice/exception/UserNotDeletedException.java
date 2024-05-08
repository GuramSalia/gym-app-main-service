package com.epam.gymappmainservice.exception;

public class UserNotDeletedException extends RuntimeException{
    public UserNotDeletedException(String message) {
        super(message);
    }
}
