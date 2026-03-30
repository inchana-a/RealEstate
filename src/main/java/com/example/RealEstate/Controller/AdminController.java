package com.example.RealEstate.Controller;

import com.example.RealEstate.Dto.AuthResponse;
import com.example.RealEstate.Dto.RegisterRequest;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<AuthResponse> createUser(@RequestBody RegisterRequest request) {
        User savedUser = adminService.createUser(request);

        AuthResponse response = new AuthResponse(
                "User created successfully",
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().name(),
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
