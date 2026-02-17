package com.yash.springecom.model.dto;

public record AuthResponse(String token, String name, String email, String role) {
}
