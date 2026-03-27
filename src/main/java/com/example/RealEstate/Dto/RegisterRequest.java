package com.example.RealEstate.Dto;

import com.example.RealEstate.Enum.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private Role role;
    private String profileImageUrl;
}
