package ru.mts.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record AddTripRequest(String fullName, String passport,
                             long bossId, @NotNull @JsonSetter("start_date") OffsetDateTime startDate,
                             @NotNull @JsonSetter("end_date") OffsetDateTime endDate, String destination,
                             String goal) {
}
