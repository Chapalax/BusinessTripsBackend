package ru.mts.hackathon.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaDataUserRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bts")
@Slf4j
public class TempController {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaDataUserRepository jpaDataUserRepository;
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

        UserDataEntity dataUser=new UserDataEntity();
        dataUser.setId(1L);
        dataUser.setUserId(user);
        dataUser.setFirstName("vova");
        dataUser.setLastName("b");
        dataUser.setFatherName("v");
        dataUser.setGrade("middle");
        dataUser.setBirthDate(OffsetDateTime.now());
        dataUser.setPhone("99999999");
        dataUser.setPassport("1111111");
        jpaDataUserRepository.save(dataUser);


        return "Welcome to Hackathon";
    }
}
