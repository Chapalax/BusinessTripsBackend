package ru.mts.hackathon.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.hackathon.domain.entities.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
