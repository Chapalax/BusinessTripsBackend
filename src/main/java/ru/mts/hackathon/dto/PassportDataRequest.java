package ru.mts.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.OffsetDateTime;

public record PassportDataRequest(long id, long userId, String firstName,
                                  String lastName, String fatherName, String grade,
                                  @JsonSetter("birth_date") OffsetDateTime birthDate,
                                  String phone, String passport) {
}

