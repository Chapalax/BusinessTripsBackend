package ru.mts.hackathon.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.StatusEnum;
import ru.mts.hackathon.domain.entities.TripEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.dto.AddTripRequest;
import ru.mts.hackathon.dto.ListTripsResponse;
import ru.mts.hackathon.dto.TripDTO;
import ru.mts.hackathon.security.CustomUserDetails;
import ru.mts.hackathon.services.interfaces.TripsServiceInterface;
import ru.mts.hackathon.services.interfaces.UserServiceInterface;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.mts.hackathon.configuration.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips")
public class TripsController {
    private final TripsServiceInterface tripsService;
    private final UserServiceInterface userService;

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public ResponseEntity<ListTripsResponse> getUserTrips(@AuthenticationPrincipal CustomUserDetails currentUser) {
        List<TripEntity> tripsList = tripsService.getAllTripsByUserId(currentUser.getId());
        ArrayList<TripDTO> tripDTOArrayList = new ArrayList<>();
        for (TripEntity tripEntity : tripsList) {
            tripDTOArrayList.add(new TripDTO(tripEntity.getId(), tripEntity.getOwnerId(), tripEntity.getBossId(),
                    tripEntity.getCreationDate(), tripEntity.getStartDate(), tripEntity.getEndDate(),
                    tripEntity.getDestination(), tripEntity.getGoal(), tripEntity.getStatus().toString(),
                    tripEntity.getComment()));
        }
        log.info("Successfully retrieved user trips: {}", tripsList);
        return ResponseEntity.ok(new ListTripsResponse(tripDTOArrayList, tripDTOArrayList.size()));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@AuthenticationPrincipal CustomUserDetails currentUser,
                                              @RequestBody AddTripRequest tripRequest) {
        Optional<UserEntity> optionalUser = userService.getUserByUsername(currentUser.getUsername());
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            String[] fullName = tripRequest.fullName().split(" ");
            if (userEntity.getFirstName().equals(fullName[1])
                    && userEntity.getLastName().equals(fullName[0])
                    && userEntity.getFatherName().equals(fullName[2])
                    && userEntity.getPassport().equals(tripRequest.passport())) {
                TripEntity trip = new TripEntity();
                trip.setOwnerId(currentUser.getId());
                trip.setBossId(tripRequest.bossId());
                trip.setCreationDate(OffsetDateTime.now());
                trip.setStartDate(tripRequest.startDate());
                trip.setEndDate(tripRequest.endDate());
                trip.setDestination(tripRequest.destination());
                trip.setGoal(tripRequest.goal());
                trip.setStatus(StatusEnum.WAITING);
                trip = tripsService.saveTrip(trip);
                log.info("Successfully created trip: {}", trip);
                return ResponseEntity.ok(new TripDTO(trip.getId(), trip.getOwnerId(), trip.getBossId(),
                        trip.getCreationDate(), trip.getStartDate(), trip.getEndDate(),
                        trip.getDestination(), trip.getGoal(), trip.getStatus().toString(),
                        trip.getComment()));
            }
        }
        log.warn("User {} tried to create trip with invalid username", currentUser.getUsername());
        return ResponseEntity.badRequest().build();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        Optional<TripEntity> tripEntityOptional = tripsService.getTripById(id);
        if (tripEntityOptional.isPresent()) {
            TripEntity tripEntity = tripEntityOptional.get();
            log.info("Successfully retrieved trip: {}", tripEntity);
            return ResponseEntity.ok(new TripDTO(tripEntity.getId(), tripEntity.getOwnerId(), tripEntity.getBossId(),
                    tripEntity.getCreationDate(), tripEntity.getStartDate(), tripEntity.getEndDate(),
                    tripEntity.getDestination(), tripEntity.getGoal(), tripEntity.getStatus().toString(),
                    tripEntity.getComment()));
        }
        log.warn("Trip with id {} not found", id);
        return ResponseEntity.notFound().build();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PatchMapping("/{id}")
    public ResponseEntity<TripDTO> updateTripById(@AuthenticationPrincipal CustomUserDetails currentUser,
                                               @PathVariable Long id, @RequestBody AddTripRequest tripRequest) {
        Optional<UserEntity> optionalUser = userService.getUserByUsername(currentUser.getUsername());
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            String[] fullName = tripRequest.fullName().split(" ");
            if (userEntity.getFirstName().equals(fullName[1])
                    && userEntity.getLastName().equals(fullName[0])
                    && userEntity.getFatherName().equals(fullName[2])
                    && userEntity.getPassport().equals(tripRequest.passport())) {
                Optional<TripEntity> tripEntityOptional = tripsService.getTripById(id);
                if (tripEntityOptional.isPresent()) {
                    TripEntity tripEntity = tripEntityOptional.get();
                    tripEntity.setBossId(tripRequest.bossId());
                    tripEntity.setCreationDate(OffsetDateTime.now());
                    tripEntity.setStartDate(tripRequest.startDate());
                    tripEntity.setEndDate(tripRequest.endDate());
                    tripEntity.setDestination(tripRequest.destination());
                    tripEntity.setGoal(tripRequest.goal());
                    tripEntity = tripsService.saveTrip(tripEntity);
                    log.info("Successfully updated trip: {}", tripEntity);
                    return ResponseEntity.ok(new TripDTO(tripEntity.getId(), tripEntity.getOwnerId(), tripEntity.getBossId(),
                            tripEntity.getCreationDate(), tripEntity.getStartDate(), tripEntity.getEndDate(),
                            tripEntity.getDestination(), tripEntity.getGoal(), tripEntity.getStatus().toString(),
                            tripEntity.getComment()));

                }
                log.warn("Trip with id {} not found", id);
                return ResponseEntity.notFound().build();
            }
        }
        log.warn("User {} tried to update trip with invalid username", currentUser.getUsername());
        return ResponseEntity.badRequest().build();
    }
}
