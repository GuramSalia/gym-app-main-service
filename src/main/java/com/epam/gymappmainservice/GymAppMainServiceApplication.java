package com.epam.gymappmainservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GymAppMainServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(GymAppMainServiceApplication.class, args);

//        TrainingTypeService trainingTypeService = BeanProvider.getTrainingTypeService();
//        TrainingType PILATES = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.PILATES);
//        TrainingType YOGA = trainingTypeService.getTrainingType(TrainingType.TrainingTypeEnum.YOGA);
//
//        TraineeService traineeServiceWithDatabaseDao = BeanProvider
//                .getTraineeService("TRAINEE_DATABASE");
//        TrainerService trainerServiceWithDatabaseDao = BeanProvider
//                .getTrainerService("TRAINER_DATABASE", "TRAINEE_DATABASE");
//        TrainingService trainingServiceWithDatabaseDao = BeanProvider
//                .getTrainingService("TRAINING_DATABASE", "TRAINER_DATABASE");
//
//        log.info(" ....... TASK-3 RELATED PART STARTS HERE  .......\n\n");
//
//        Trainer trainerTim = trainerServiceWithDatabaseDao.findByUsernameAndPassword("Tim.Smith", "123");
//        trainerTim.setUsername("NEW.USERNAME");
//        trainerTim = trainerServiceWithDatabaseDao.update(trainerTim, "Tim.Smith", "123");
//        log.info("updated trainer: " + trainerTim);


        log.info("\n\n swagger at: 'http://localhost:8080/swagger-ui/index.html'\n\n");
        log.info("\n\n api-docs at: 'http://localhost:8080/v3/api-docs'");

        log.info("\n\n>>>> END of TASK-3 RELATED PART  ==============\n\n");
    }
}
