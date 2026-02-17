package com.yash.springecom.model.dto;

public record RegisterRequest(String name, String email, String password, String role) {
}
