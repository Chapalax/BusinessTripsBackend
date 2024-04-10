package ru.mts.hackathon.domain.repositories;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.mts.hackathon.domain.entities.UserEntity;

@RequiredArgsConstructor
public class UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserEntity add(@NotNull UserEntity object) {
        return jpaUserRepository.save(object);
    }
}
