package ru.mts.hackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.hackathon.domain.repositories.UserRepository;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TempController {
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<HttpStatus> testToBD() {
        log.info("GET REQUEST");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
