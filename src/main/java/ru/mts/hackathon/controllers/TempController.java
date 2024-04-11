package ru.mts.hackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("bts")
@Slf4j
public class TempController {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> testToBD() {
        log.info("GET REQUEST");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public String welcome() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("login@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles("ADMIN");
        user.setEnabled(true);
        userRepository.save(user);
        return "Welcome to Hackathon";
    }
}
