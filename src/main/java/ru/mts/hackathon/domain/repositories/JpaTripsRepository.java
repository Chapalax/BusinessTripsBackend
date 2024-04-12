package ru.mts.hackathon.domain.repositories;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.hackathon.domain.entities.TripEntity;

import java.util.List;
import java.util.Optional;

public interface JpaTripsRepository extends JpaRepository<TripEntity, Long> {

    Optional<TripEntity> findById(@NotNull Long id);

    List<TripEntity> findAllByOwnerId(@NotNull Long id);

    List<TripEntity> findAllByBossId(@NotNull Long id);
}
