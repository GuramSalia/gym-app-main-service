package com.epam.gymappmainservice.config.storage;

import com.epam.gymappmainservice.model.Trainee;
import com.epam.gymappmainservice.model.Trainer;
import com.epam.gymappmainservice.model.Training;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Setter
@Getter
@Component
public class InMemoryStorage {

    private Map<Integer, Trainee> trainees = new ConcurrentHashMap<>();
    private Map<Integer, Trainer> trainers = new ConcurrentHashMap<>();
    private Map<Integer, Training> trainings = new ConcurrentHashMap<>();

    public void clearStorage(Map<Integer, Objects> storage) {
        log.info("Clearing storage");
        storage.clear();
    }

    public void clearStorage() {
        log.info("Clearing storage");
        trainees.clear();
        trainers.clear();
        trainings.clear();
    }
}
