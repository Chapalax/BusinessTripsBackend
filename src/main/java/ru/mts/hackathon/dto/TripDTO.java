package ru.mts.hackathon.dto;

import jakarta.annotation.Nullable;

import java.time.OffsetDateTime;

public record TripDTO(long id, long ownerId, long bossId, OffsetDateTime creationDate,
                      OffsetDateTime startDate, OffsetDateTime endDate, String destination,
                      String goal, String status, @Nullable String comment) {
}
