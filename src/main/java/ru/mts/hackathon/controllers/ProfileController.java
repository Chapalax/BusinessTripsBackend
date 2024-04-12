package ru.mts.hackathon.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.dto.UserDTO;
import ru.mts.hackathon.mappers.intrefaces.MapperUserDTO;
import ru.mts.hackathon.security.CustomUserDetails;
import ru.mts.hackathon.services.interfaces.UserServiceInterface;

import static ru.mts.hackathon.configuration.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserServiceInterface userDataService;
    private final MapperUserDTO mapperUserDTO;

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public ResponseEntity<UserDTO> getCurrentUserData(@AuthenticationPrincipal CustomUserDetails currentUser) {
        log.info("Successfully requested to get current user profile");
        return ResponseEntity.ok(
                mapperUserDTO.toUserDTO(
                        userDataService.validateAndGetUserByUsername(currentUser.getUsername())
                )
        );
    }


}
