package com.example.RealEstate.Controller;

import com.example.RealEstate.Dto.AuthResponse;
import com.example.RealEstate.Dto.LoginRequest;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    //  REGISTER
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {

        User savedUser = userService.registerUser(user);

        AuthResponse response = new AuthResponse(
                "User registered successfully",
                savedUser.getUserId(),
                savedUser.getEmail()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //  LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getEmail(), request.getPassword());

        AuthResponse response = new AuthResponse(
                "Login successful",
                user.getUserId(),
                user.getEmail()
        );

        return ResponseEntity.ok(response);
    }
}
