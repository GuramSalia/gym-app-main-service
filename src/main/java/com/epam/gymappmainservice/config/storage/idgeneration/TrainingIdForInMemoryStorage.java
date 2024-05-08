package com.epam.gymappmainservice.config.storage.idgeneration;

public class TrainingIdForInMemoryStorage {
    private static int userId = 3;
    public static int getNewId() {
        return userId++;
    }
}
