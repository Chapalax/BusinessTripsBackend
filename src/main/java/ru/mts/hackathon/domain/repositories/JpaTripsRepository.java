package ru.mts.hackathon.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.hackathon.domain.entities.TripEntity;

public interface JpaTripsRepository extends JpaRepository<TripEntity, Long> {

}
