package ru.mts.hackathon.services.interfaces;

import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDataServiceInterface {
    List<UserDataEntity> getAllUserData();

    Optional<UserDataEntity> getUserDataByUser(UserEntity user);

    Optional<UserDataEntity> getUserDataByEmail(String email);

    Optional<UserDataEntity> getUserDataByPhone(String phone);

    Optional<UserDataEntity> getUserDataByPassport(String passport);

    boolean hasUserDataWithEmail(String email);

    boolean hasUserDataWithPhoneNumber(String phoneNumber);

    boolean hasUserDataWithPassport(String passport);

    UserDataEntity saveUserData(UserDataEntity userData);

    void deleteUserData(UserDataEntity userData);
}
