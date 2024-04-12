package ru.mts.hackathon.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.dto.UserDTO;
import ru.mts.hackathon.dto.LoginRequest;
import ru.mts.hackathon.services.interfaces.UserServiceInterface;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceInterface userService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<UserEntity> userOptional = userService
                .validUsernameAndPassword(loginRequest.username(), loginRequest.password());
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            return ResponseEntity.ok(new UserDTO(user.getId(), user.getRole(), user.getFirstName(),
                    user.getLastName(), user.getFatherName(), user.getGrade(),
                    user.getBirthDate(), user.getPhone(), user.getEmail(), user.getPassport()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
