package com.springsecurity.model.dto;

public record LoginResponseDto(String username,String status,String jwtToken) {
}
