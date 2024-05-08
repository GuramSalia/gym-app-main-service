package com.epam.gymappmainservice.service;

import com.epam.gymappmainservice.dao.TraineeDAO;
import com.epam.gymappmainservice.dao.TrainerDAO;
import com.epam.gymappmainservice.exception.UnauthorizedException;
import com.epam.gymappmainservice.exception.UserNotFoundException;
import com.epam.gymappmainservice.model.Trainee;
import com.epam.gymappmainservice.model.Trainer;
import com.epam.gymappmainservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;

    @Autowired
    public UserService(
            TraineeService traineeService,
            TraineeDAO traineeDAO,
            TrainerService trainerService,
            TrainerDAO trainerDAO
    ) {
        this.traineeService = traineeService;
        this.traineeDAO = traineeDAO;
        this.trainerService = trainerService;
        this.trainerDAO = trainerDAO;
    }

    public void updatePassword(String username, String currentPassword, String newPassword) {

        Optional<Trainee> traineeOptional = traineeDAO.findByUsername(username);
        Optional<Trainer> trainerOptional = trainerDAO.findByUsername(username);

        if (traineeOptional.isEmpty() && trainerOptional.isEmpty()) {
            throw new UnauthorizedException("username or password is invalid");
        }

        traineeOptional.ifPresent(
                trainee -> traineeService.updatePassword(trainee, username, currentPassword, newPassword));
        trainerOptional.ifPresent(
                trainer -> trainerService.updatePassword(trainer, username, currentPassword, newPassword));
    }

    public User getUser(String username) {
        Optional<Trainee> traineeOptional = traineeDAO.findByUsername(username);
        Optional<Trainer> trainerOptional = trainerDAO.findByUsername(username);
        if (traineeOptional.isEmpty() && trainerOptional.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        if (traineeOptional.isPresent()) {
            return traineeOptional.get();
        }
        return trainerOptional.get();
    }
}
