package ru.mts.hackathon.dto;

import org.hibernate.validator.constraints.URL;

public record LoginRequest(@URL String username, String password) {
}
