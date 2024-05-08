package com.epam.gymappmainservice.repository;

import com.epam.gymappmainservice.model.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, Integer> {
        TrainingType findByTrainingType(TrainingType.TrainingTypeEnum trainingType);

}
