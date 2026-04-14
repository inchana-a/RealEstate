package com.example.RealEstate.Controller;

import com.example.RealEstate.Dto.AuthResponse;
import com.example.RealEstate.Dto.LoginRequest;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Service.UserService;
import com.example.RealEstate.security.CustomUserDetailsService;
import com.example.RealEstate.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
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
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}