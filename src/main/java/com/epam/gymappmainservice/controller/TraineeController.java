package com.epam.gymappmainservice.controller;

import com.epam.gymappmainservice.api.*;
import com.epam.gymappmainservice.aspect.LogRestDetails;
import com.epam.gymappmainservice.exception.UnauthorizedException;
import com.epam.gymappmainservice.global.EndpointSuccessCounter;
import com.epam.gymappmainservice.model.Trainee;
import com.epam.gymappmainservice.model.Trainer;
import com.epam.gymappmainservice.service.TraineeService;
import com.epam.gymappmainservice.service.TrainerService;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@LogRestDetails
@RestController
@Slf4j
public class TraineeController {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final EndpointSuccessCounter endpointSuccessCounter;

    public TraineeController(
            TraineeService traineeService,
            TrainerService trainerService,
            EndpointSuccessCounter endpointSuccessCounter,
            MeterRegistry meterRegistry
    ) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.endpointSuccessCounter = endpointSuccessCounter;
    }

    @GetMapping("/trainee-get")
    @Operation(summary = "Get Trainee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainee retrieved successfully")
    })
    public ResponseEntity<TraineeDTOWithTrainersList> getTrainee(
            @Valid @RequestBody UsernamePassword usernamePassword
    ) {
        String username = usernamePassword.getUsername();
        checkUsernameAgainstJwtUsername(username);
        Trainee trainee = traineeService.findByUsername(username);
        log.info("\n\nendpoint > /trainee-get > trainee: " + trainee + "\n");
        TraineeDTOWithTrainersList traineeDTO = getTraineeDTOWithTrainersList(trainee);
        endpointSuccessCounter.incrementCounter("GET/trainee-get");
        return ResponseEntity.ok(traineeDTO);
    }

    @PutMapping("/trainee")
    @Operation(summary = "Update Trainee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainee updated successfully")
    })
    public ResponseEntity<TraineeDTOUpdated> updateTrainee(
            @Valid @RequestBody TraineeUpdateRequest traineeUpdateRequest
    ) {
        String username = traineeUpdateRequest.getUsername();
        checkUsernameAgainstJwtUsername(username);
        Trainee trainee = traineeService.findByUsername(username);
        TraineeDTOUpdated traineeDTO = getTraineeDTOUpdated(traineeUpdateRequest, trainee);
        endpointSuccessCounter.incrementCounter("PUT/trainee");
        return ResponseEntity.ok(traineeDTO);
    }

    @DeleteMapping("/trainee-delete")
    @Operation(summary = "Delete Trainee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trainee deleted successfully")
    })
    public ResponseEntity<Void> deleteTrainee(
            @Valid @RequestBody UsernamePassword usernamePassword
    ) {
        String username = usernamePassword.getUsername();
        checkUsernameAgainstJwtUsername(username);
        String password = usernamePassword.getPassword();
        traineeService.delete(username, password);
        endpointSuccessCounter.incrementCounter("DELETE/trainee-delete");
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    @PutMapping("/trainee/update-trainers-list")
    @Operation(summary = "Update Trainee's Trainers List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trainers list updated successfully")
    })
    public ResponseEntity<List<TrainerDTOForTrainersList>> updateTrainersList(
            @Valid @RequestBody TraineeUpdateTrainersListRequest traineeUpdateTrainersListRequest
    ) {
        String username = traineeUpdateTrainersListRequest.getUsername();
        checkUsernameAgainstJwtUsername(username);
        List<String> trainerUsernames = traineeUpdateTrainersListRequest.getTrainerUsernames();
        Trainee trainee = traineeService.findByUsername(username);
        List<TrainerDTOForTrainersList> trainerListDTO = getTrainerListDTO(trainerUsernames, trainee);
        endpointSuccessCounter.incrementCounter("PUT/trainee/update-trainers-list");
        return ResponseEntity.ok().body(trainerListDTO);
    }

    @PatchMapping("/trainee/activate")
    @Operation(summary = "Activate Trainee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trainee activated successfully")
    })
    public ResponseEntity<Void> activateTrainee(
            @Valid @RequestBody UsernamePassword usernamePassword
    ) {
        String username = usernamePassword.getUsername();
        checkUsernameAgainstJwtUsername(username);
        Trainee trainee = traineeService.findByUsername(username);
        trainee.setIsActive(true);
        traineeService.update(trainee);
        endpointSuccessCounter.incrementCounter("PATCH/trainee/activate");
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    @PatchMapping("/trainee/deactivate")
    @Operation(summary = "Deactivate Trainee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trainee deactivated successfully")
    })
    public ResponseEntity<Void> deactivateTrainee(
            @Valid @RequestBody UsernamePassword usernamePassword
    ) {
        String username = usernamePassword.getUsername();
        checkUsernameAgainstJwtUsername(username);
        Trainee trainee = traineeService.findByUsername(username);
        trainee.setIsActive(false);
        traineeService.update(trainee);
        endpointSuccessCounter.incrementCounter("PATCH/trainee/deactivate");
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    private static void checkUsernameAgainstJwtUsername(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameFromJwt = authentication.getName();
        if (!username.equals(usernameFromJwt)) {
            throw new UnauthorizedException("you have no access to the data of this username");
        }
    }

    private static TraineeDTOWithTrainersList getTraineeDTOWithTrainersList(Trainee trainee) {
        TraineeDTOWithTrainersList traineeDTO = new TraineeDTOWithTrainersList();
        traineeDTO.setFirstName(trainee.getFirstName());
        traineeDTO.setLastName(trainee.getLastName());
        traineeDTO.setDateOfBirth(trainee.getDob());
        traineeDTO.setAddress(trainee.getAddress());
        traineeDTO.setActive(trainee.getIsActive());

        List<TrainerDTOForTrainersList> trainerList = trainee
                .getTrainers()
                .stream()
                .map(trainer -> {
                    TrainerDTOForTrainersList trainerDTOForTrainersList = new TrainerDTOForTrainersList();
                    trainerDTOForTrainersList.setUsername(trainer.getUsername());
                    trainerDTOForTrainersList.setFirstName(trainer.getFirstName());
                    trainerDTOForTrainersList.setLastName(trainer.getLastName());
                    trainerDTOForTrainersList.setSpecialization(trainer.getSpecialization());
                    return trainerDTOForTrainersList;
                }).toList();

        traineeDTO.setTrainers(trainerList);
        return traineeDTO;
    }

    private TraineeDTOUpdated getTraineeDTOUpdated(TraineeUpdateRequest traineeUpdateRequest, Trainee trainee) {
        trainee.setFirstName(traineeUpdateRequest.getFirstName());
        trainee.setLastName(traineeUpdateRequest.getLastName());
        trainee.setDob(traineeUpdateRequest.getDateOfBirth());
        trainee.setAddress(traineeUpdateRequest.getAddress());
        trainee.setIsActive(traineeUpdateRequest.getIsActive());

        Trainee updatedTrainee = traineeService.update(trainee);

        TraineeDTOUpdated traineeDTO = new TraineeDTOUpdated();
        traineeDTO.setUsername(updatedTrainee.getUsername());
        traineeDTO.setFirstName(updatedTrainee.getFirstName());
        traineeDTO.setLastName(updatedTrainee.getLastName());
        traineeDTO.setDateOfBirth(updatedTrainee.getDob());
        traineeDTO.setAddress(updatedTrainee.getAddress());
        traineeDTO.setIsActive(updatedTrainee.getIsActive());

        List<TrainerDTOForTrainersList> trainerList = updatedTrainee
                .getTrainers()
                .stream()
                .map(trainer -> {
                    TrainerDTOForTrainersList trainerDTOForTrainersList = new TrainerDTOForTrainersList();
                    trainerDTOForTrainersList.setUsername(trainer.getUsername());
                    trainerDTOForTrainersList.setFirstName(trainer.getFirstName());
                    trainerDTOForTrainersList.setLastName(trainer.getLastName());
                    trainerDTOForTrainersList.setSpecialization(trainer.getSpecialization());
                    return trainerDTOForTrainersList;
                }).toList();

        traineeDTO.setTrainers(trainerList);
        return traineeDTO;
    }

    private List<TrainerDTOForTrainersList> getTrainerListDTO(
            List<String> trainerUsernames,
            Trainee trainee
    ) {
        List<Trainer> trainers = trainerUsernames
                .stream()
                .map(trainerService::findByUsername).toList();
        Set<Trainer> trainerSet = new HashSet<Trainer>(trainers);
        trainee.setTrainers(trainerSet);
        traineeService.update(trainee);

        return trainerSet
                .stream()
                .map(trainer -> {
                    return new TrainerDTOForTrainersList(
                            trainer.getUsername(),
                            trainer.getFirstName(),
                            trainer.getLastName(),
                            trainer.getSpecialization());
                }).toList();
    }
}
