package ru.mts.hackathon.dto;

import jakarta.validation.constraints.Email;

public record LoginRequest(@Email String username, String password) {
}
