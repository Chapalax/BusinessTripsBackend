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
import ru.mts.hackathon.dto.ChangeTripStatusDTO;
import ru.mts.hackathon.dto.ListTripsResponse;
import ru.mts.hackathon.dto.TripDTO;
import ru.mts.hackathon.dto.UserDTO;
import ru.mts.hackathon.mappers.intrefaces.MapperUserDTO;
import ru.mts.hackathon.security.CustomUserDetails;
import ru.mts.hackathon.services.interfaces.TripsServiceInterface;
import ru.mts.hackathon.services.interfaces.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.mts.hackathon.configuration.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TripsServiceInterface tripsService;
    private final UserServiceInterface userService;
    private final MapperUserDTO mapperUserDTO;

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllAdmins() {
        List<UserEntity> userEntities = userService.getAllWithRole("ADMIN");
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDTOs.add(mapperUserDTO.toUserDTO(userEntity));
        }
        return ResponseEntity.ok(userDTOs);
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/trips")
    public ResponseEntity<ListTripsResponse> getAdminTrips(@AuthenticationPrincipal CustomUserDetails currentUser) {
        List<TripEntity> tripsList = tripsService.getAllTripsByBossId(currentUser.getId());
        ArrayList<TripDTO> tripDTOArrayList = new ArrayList<>();
        for (TripEntity tripEntity : tripsList) {
            tripDTOArrayList.add(new TripDTO(tripEntity.getId(), tripEntity.getOwnerId(), tripEntity.getBossId(),
                    tripEntity.getCreationDate(), tripEntity.getStartDate(), tripEntity.getEndDate(),
                    tripEntity.getDestination(), tripEntity.getGoal(), tripEntity.getStatus().toString(),
                    tripEntity.getComment()));
        }
        log.info("Get admin trips: {}", tripsList);
        return ResponseEntity.ok(new ListTripsResponse(tripDTOArrayList, tripDTOArrayList.size()));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/trips/{id}")
    public ResponseEntity<TripDTO> getTripByAdmin(@PathVariable Long id) {
        Optional<TripEntity> tripEntityOptional = tripsService.getTripById(id);
        if (tripEntityOptional.isPresent()) {
            TripEntity tripEntity = tripEntityOptional.get();
            log.info("Retrieved trip with id {}", id);
            return ResponseEntity.ok(new TripDTO(tripEntity.getId(), tripEntity.getOwnerId(), tripEntity.getBossId(),
                    tripEntity.getCreationDate(), tripEntity.getStartDate(), tripEntity.getEndDate(),
                    tripEntity.getDestination(), tripEntity.getGoal(), tripEntity.getStatus().toString(),
                    tripEntity.getComment()));
        }
        log.warn("No trip with id {}", id);
        return ResponseEntity.notFound().build();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PatchMapping("/trips/{id}")
    public ResponseEntity<TripDTO> updateTripStatus(@PathVariable Long id,
                                                    @RequestBody ChangeTripStatusDTO tripStatusDTO) {
        Optional<TripEntity> optionalTrip = tripsService.getTripById(id);
        if (optionalTrip.isPresent()) {
            TripEntity tripEntity = optionalTrip.get();
            tripEntity.setStatus(StatusEnum.valueOf(tripStatusDTO.status()));
            if(tripStatusDTO.comment() != null) {
                tripEntity.setComment(tripStatusDTO.comment());
            }
            tripEntity = tripsService.saveTrip(tripEntity);
            log.info("UPDATED STATUS: {}", tripEntity.getStatus());
            return ResponseEntity.ok(new TripDTO(tripEntity.getId(), tripEntity.getOwnerId(), tripEntity.getBossId(),
                    tripEntity.getCreationDate(), tripEntity.getStartDate(), tripEntity.getEndDate(),
                    tripEntity.getDestination(), tripEntity.getGoal(), tripEntity.getStatus().toString(),
                    tripEntity.getComment()));
        }
        log.warn("Trip with id {} not found", id);
        return ResponseEntity.notFound().build();
    }
}
