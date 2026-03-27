package com.example.RealEstate.Service;

import com.example.RealEstate.Dto.RegisterRequest;
import com.example.RealEstate.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserService userService;

    public User createUser(RegisterRequest request) {
        return userService.registerUser(request, true);
    }
}
