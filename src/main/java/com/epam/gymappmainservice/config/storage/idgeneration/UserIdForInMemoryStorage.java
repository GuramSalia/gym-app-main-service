package com.epam.gymappmainservice.config.storage.idgeneration;

public class UserIdForInMemoryStorage {
    private static int userId = 5;
    public static int getNewId() {return userId++;}
}
