package ru.mts.hackathon.dto;

import java.util.ArrayList;

public record ListTripsResponse(ArrayList<TripDTO> trips, int size) {
}
