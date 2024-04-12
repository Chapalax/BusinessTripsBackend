package ru.mts.hackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TempController {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
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
        user.setPhone("8521287863");
        user.setPassport("123456789");
        userRepository.save(user);
        return "Welcome to Hackathon";
    }
}
