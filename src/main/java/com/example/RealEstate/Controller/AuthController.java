package com.example.RealEstate.Controller;

import com.example.RealEstate.Dto.AuthResponse;
import com.example.RealEstate.Dto.ForgotPasswordRequest;
import com.example.RealEstate.Dto.LoginRequest;
import com.example.RealEstate.Dto.RegisterRequest;
import com.example.RealEstate.Enum.Role;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Service.UserService;
import com.example.RealEstate.security.CustomUserDetailsService;
import com.example.RealEstate.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    //  REGISTER
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        Role role = request.getRole();
        if (role == Role.ADMIN) {
            throw new IllegalArgumentException("Public registration cannot create ADMIN users");
        }

        User savedUser = userService.registerUser(request);

        AuthResponse response = new AuthResponse(
                "User registered successfully",
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getRole().name(),
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //  LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getEmail(), request.getPassword());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        AuthResponse response = new AuthResponse(
                "Login successful",
                user.getUserId(),
                user.getEmail(),
                user.getRole().name(),
                token
        );

        return ResponseEntity.ok(response);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {

        String response = userService.forgotPassword(
                request.getEmail(),
                request.getNewPassword()
        );

        return ResponseEntity.ok(response);
    }
}
