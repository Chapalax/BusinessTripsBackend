package ru.mts.hackathon.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaUserDataRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class PassportControllers {
    @Autowired
    private JpaUserDataRepository jpaUserDataRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @GetMapping
    public @ResponseBody UserDataEntity getData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UserEntity> user = jpaUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Optional<UserDataEntity> data = jpaUserDataRepository.findByUserId(user.get());
        if (data.isEmpty()) {
            throw new UsernameNotFoundException("Data not found");
        }
        return data.get();
    }


}
