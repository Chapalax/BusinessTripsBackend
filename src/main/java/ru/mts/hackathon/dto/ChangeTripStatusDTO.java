package ru.mts.hackathon.dto;

import jakarta.annotation.Nullable;

public record ChangeTripStatusDTO(String status, @Nullable String comment) {
}
