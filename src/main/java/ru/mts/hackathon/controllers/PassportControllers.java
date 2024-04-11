package ru.mts.hackathon.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.repositories.JpaDataUserRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu/passport")
public class PassportControllers {
    @Autowired
    private JpaDataUserRepository jpaDataUserRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @GetMapping
    public @ResponseBody UserDataEntity getData(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Long id = jpaUserRepository.findByUsername(username).get().getId();
        return jpaDataUserRepository.findByUserId(id).get();
    }


}
