package ru.mts.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;

public record PassportDataRequest( Long id,  Long userId, String firstName,
                                  String lastName, String fatherName, String grade,
                                  @NotNull @JsonSetter("birth_date") OffsetDateTime birthDate,
                                  String phone, String passport) {
}

