package com.example.RealEstate.Model;

import com.example.RealEstate.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Role role; // buyer, seller, agent, admin

    private Boolean Verified;
    private LocalDateTime createdAt;
}
