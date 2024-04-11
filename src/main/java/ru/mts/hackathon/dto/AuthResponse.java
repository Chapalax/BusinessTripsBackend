package ru.mts.hackathon.dto;

import java.time.OffsetDateTime;

public record AuthResponse(long id, String role, String firstName, String lastName,
                           String fatherName, String grade, OffsetDateTime birthDate,
                           String phone, String email, String passport) {
}
