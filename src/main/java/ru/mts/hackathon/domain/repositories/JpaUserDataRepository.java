package ru.mts.hackathon.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;

import java.util.Optional;

@Repository
public interface JpaUserDataRepository extends JpaRepository<UserDataEntity, Long> {

    Optional<UserDataEntity> findByUserId(UserEntity userId);

    Optional<UserDataEntity> findByEmail(String email);

    Optional<UserDataEntity> findByPhone(String phone);

    Optional<UserDataEntity> findByPassport(String passport);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByPassport(String passport);
}
