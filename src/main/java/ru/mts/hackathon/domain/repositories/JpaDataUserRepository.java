package ru.mts.hackathon.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.hackathon.domain.entities.UserDataEntity;
import ru.mts.hackathon.domain.entities.UserEntity;

import java.util.Optional;

@Repository
public interface JpaDataUserRepository extends JpaRepository<UserDataEntity, Long> {
    Optional<UserDataEntity> findByUserId(UserEntity userId);
}
