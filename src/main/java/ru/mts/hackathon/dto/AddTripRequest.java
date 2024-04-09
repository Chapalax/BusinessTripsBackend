package ru.mts.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record AddTripRequest(long author, long bossId, String destination,
                             @NotNull @JsonSetter("start_date") OffsetDateTime startDate,
                             @NotNull @JsonSetter("end_date") OffsetDateTime endDate,
                             String goal) {
}
