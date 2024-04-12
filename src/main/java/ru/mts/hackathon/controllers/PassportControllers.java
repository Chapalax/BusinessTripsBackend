package ru.mts.hackathon.controllers;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaDataUserRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;
import ru.mts.hackathon.dto.PassportDataRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/menu/passport")
public class PassportControllers {
    @Autowired
    private JpaDataUserRepository jpaDataUserRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @GetMapping()
    public @ResponseBody UserDataEntity getData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UserEntity> user = jpaUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Optional<UserDataEntity> data = jpaDataUserRepository.findByUserId(user.get());
        if (data.isEmpty()) {
            throw new UsernameNotFoundException("Data not found");
        }
        return data.get();
    }

    @PostMapping()
    public void postData(@RequestBody PassportDataRequest data){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Optional<UserEntity> user = jpaUserRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Optional<UserDataEntity> oldData = jpaDataUserRepository.findByUserId(user.get());
        if (oldData.isEmpty()) {
            throw new UsernameNotFoundException("Data not found");
        }
        UserDataEntity dataUser=new UserDataEntity();
        dataUser.setId(data.id());
        dataUser.setUserId(user.get());
        dataUser.setFirstName(data.firstName());
        dataUser.setLastName(data.lastName());
        dataUser.setFatherName(data.fatherName());
        dataUser.setGrade(data.grade());
        dataUser.setBirthDate(data.birthDate());
        dataUser.setPhone(data.phone());
        dataUser.setPassport(data.passport());
        jpaDataUserRepository.save(dataUser);
    }



}
