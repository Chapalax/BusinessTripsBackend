package ru.mts.hackathon.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;
import ru.mts.hackathon.domain.repositories.JpaUserDataRepository;
import ru.mts.hackathon.services.interfaces.UserDataServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataService implements UserDataServiceInterface {
    private final JpaUserDataRepository userDataRepository;

    @Override
    public List<UserDataEntity> getAllUserData() {
        return userDataRepository.findAll();
    }

    @Override
    public Optional<UserDataEntity> getUserDataByUser(UserEntity user) {
        return userDataRepository.findByUserId(user);
    }

    @Override
    public Optional<UserDataEntity> getUserDataByEmail(String email) {
        return userDataRepository.findByEmail(email);
    }

    @Override
    public Optional<UserDataEntity> getUserDataByPhone(String phone) {
        return userDataRepository.findByPhone(phone);
    }

    @Override
    public Optional<UserDataEntity> getUserDataByPassport(String passport) {
        return userDataRepository.findByPassport(passport);
    }

    @Override
    public boolean hasUserDataWithEmail(String email) {
        return userDataRepository.existsByEmail(email);
    }

    @Override
    public boolean hasUserDataWithPhoneNumber(String phoneNumber) {
        return userDataRepository.existsByPhone(phoneNumber);
    }

    @Override
    public boolean hasUserDataWithPassport(String passport) {
        return userDataRepository.existsByPassport(passport);
    }

    @Override
    public UserDataEntity saveUserData(UserDataEntity userData) {
        return userDataRepository.save(userData);
    }

    @Override
    public void deleteUserData(UserDataEntity userData) {
        userDataRepository.delete(userData);
    }
}
