package ru.mts.hackathon.dto;

import org.hibernate.validator.constraints.URL;

public record AuthenticationRequest(@URL String username, String password) {
}
