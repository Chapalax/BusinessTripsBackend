package ru.mts.hackathon.services.interfaces;

import ru.mts.hackathon.domain.entities.TripEntity;

import java.util.List;
import java.util.Optional;

public interface TripsServiceInterface {
    List<TripEntity> getAllTripsByUserId(long id);

    List<TripEntity> getAllTripsByBossId(long id);

    Optional<TripEntity> getTripById(long id);

    TripEntity saveTrip(TripEntity trip);
}
