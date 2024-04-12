package ru.mts.hackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.StatusEnum;
import ru.mts.hackathon.domain.entities.TripEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaTripsRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TempController {
    private final JpaUserRepository userRepository;
    private final JpaTripsRepository tripsRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public String testToBD() {
        log.info("GET REQUEST");
        return "Test done!";
    }

    @GetMapping("/welcome")
    public String welcome() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("login@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRole("ADMIN");
        user.setEnabled(true);
        user.setFirstName("Max");
        user.setLastName("Berezhnoy");
        user.setFatherName("Sergeevich");
        user.setGrade("Senior Ramus Developer");
        user.setBirthDate(OffsetDateTime.now());
        user.setEmail("max@gmail.com");
        user.setPhone("+73408627956");
        user.setPassport("7415 445218");
        userRepository.save(user);

        user = new UserEntity();
        user.setId(2L);
        user.setUsername("user@gmail.com");
        user.setPassword(passwordEncoder.encode("secret"));
        user.setRole("USER");
        user.setEnabled(true);
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setFatherName("Ivanovich");
        user.setGrade("Intern BPMN Developer");
        user.setBirthDate(OffsetDateTime.now());
        user.setEmail("ivan@gmail.com");
        user.setPhone("+78523468555");
        user.setPassport("7415 456123");
        userRepository.save(user);

        TripEntity trip = new TripEntity();
        trip.setId(1);
        trip.setOwnerId(2L);
        trip.setBossId(1L);
        trip.setCreationDate(OffsetDateTime.now());
        trip.setStartDate(OffsetDateTime.now());
        trip.setEndDate(OffsetDateTime.now());
        trip.setDestination("Самара");
        trip.setGoal("На курорт");
        trip.setStatus(StatusEnum.WAITING);
        tripsRepository.save(trip);
        return "Welcome to Hackathon";
    }
}
