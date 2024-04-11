package ru.mts.hackathon.controllers;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaDataUserRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;
import ru.mts.hackathon.dto.PassportDataRequest;

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

    @GetMapping
    public void postData(@RequestBody PassportDataRequest data){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserDataEntity dataUser=new UserDataEntity();
        dataUser.setId(data.id());
        UserEntity user=jpaUserRepository.findByUsername(username).get();
        dataUser.setUserId(user);
        dataUser.setFirstName(data.firstName());
        dataUser.setLastName(data.lastName());
        dataUser.setFatherName(data.fatherName());
        dataUser.setGrade(data.grade());
        dataUser.setBirthDate(data.birthDate());
        dataUser.setPhone(data.phone());
        dataUser.setPassport(data.passport());
        jpaDataUserRepository.update(dataUser);
    }



}
