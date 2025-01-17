package com.epam.gymappmainservice.dao;

import com.epam.gymappmainservice.model.Training;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingDAO {
    public Optional<Training> create(Training training);

    public Optional<Training> getById(int id);

    public List<Training> getTrainings();

    public List<Training> getTrainingsByTraineeAndOtherFilters(
            String traineeUsername,
            Date startDate,
            Date endDate,
            String trainerUsername,
            String trainingTypeName);

    public List<Training> getTrainingsByTrainerAndOtherFilters(
            String traineeUsername,
            Date startDate,
            Date endDate,
            String trainerUsername);
}
