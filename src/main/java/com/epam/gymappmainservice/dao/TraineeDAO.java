package com.epam.gymappmainservice.dao;

import com.epam.gymappmainservice.model.Trainee;
import com.epam.gymappmainservice.utils.ImplementsFindByUsernameAndPassword;

import java.util.List;
import java.util.Optional;

public interface TraineeDAO extends ImplementsFindByUsernameAndPassword {
    public Optional<Trainee> create(Trainee trainee);

    public Optional<Trainee> update(Trainee trainee);

    public boolean delete(Trainee trainee);

    public Optional<Trainee> getById(int id);

    public List<Trainee> getTrainees();

    public Optional<Trainee> findByUsername(String username);

    public Optional<Trainee> findByUsernameAndPassword(String username, String password);

    public Optional<Trainee> findByUsernameWithQuery(String username);
}
