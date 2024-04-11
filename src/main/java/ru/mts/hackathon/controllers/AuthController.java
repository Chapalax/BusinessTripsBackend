package ru.mts.hackathon.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.dto.AuthResponse;
import ru.mts.hackathon.dto.LoginRequest;
import ru.mts.hackathon.services.interfaces.UserDataServiceInterface;
import ru.mts.hackathon.services.interfaces.UserServiceInterface;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceInterface userService;

    private final UserDataServiceInterface userDataService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<UserEntity> userOptional = userService
                .validUsernameAndPassword(loginRequest.username(), loginRequest.password());
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            Optional<UserDataEntity> userDataOptional = userDataService.getUserDataByUser(user);
            if (userDataOptional.isPresent()) {
                UserDataEntity userData = userDataOptional.get();
                return ResponseEntity.ok(new AuthResponse(user.getId(), user.getRole(), userData.getFirstName(),
                        userData.getLastName(), userData.getFatherName(), userData.getGrade(),
                        userData.getBirthDate(), userData.getPhone(), userData.getEmail(), userData.getPassport()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
