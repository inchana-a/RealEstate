package com.example.RealEstate.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private Long userId;
    private String email;
    private String role;
    private String token;
}
