package ru.mts.hackathon.services.interfaces;

import org.hibernate.validator.constraints.URL;
import ru.mts.hackathon.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserByUsername(String username);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(@URL String email);

    boolean userIsEnabled(String username);

    UserEntity validateAndGetUserByUsername(String username);

    UserEntity saveUser(UserEntity user);

    void deleteUser(UserEntity user);

    Optional<UserEntity> validUsernameAndPassword(String username, String password);
}
