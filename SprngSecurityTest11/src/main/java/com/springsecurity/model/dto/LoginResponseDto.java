package com.springsecurity.model.dto;

public record LoginResponseDto(String email,String role,String jwtToken) {
}
