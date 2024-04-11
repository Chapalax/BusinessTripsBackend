package ru.mts.hackathon.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.hackathon.domain.entities.UserDataEntity;

import java.util.Optional;

@Repository
public interface JpaDataUserRepository extends JpaRepository<DataUserRepository, Long> {
    Optional<UserDataEntity> findByUserId(Long userId);
    void update(UserDataEntity userDataEntity);
}
