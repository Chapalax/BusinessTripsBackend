package ru.mts.hackathon.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.hackathon.domain.entities.TripEntity;
import ru.mts.hackathon.domain.repositories.JpaTripsRepository;
import ru.mts.hackathon.services.interfaces.TripsServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripsService implements TripsServiceInterface {
    private final JpaTripsRepository tripsRepository;

    @Override
    public List<TripEntity> getAllTripsByUserId(long id) {
        return tripsRepository.findAllByOwnerId(id);
    }

    @Override
    public List<TripEntity> getAllTripsByBossId(long id) {
        return tripsRepository.findAllByBossId(id);
    }

    @Override
    public Optional<TripEntity> getTripById(long id) {
        return tripsRepository.findById(id);
    }

    @Override
    public TripEntity saveTrip(TripEntity trip) {
        return tripsRepository.save(trip);
    }
}
