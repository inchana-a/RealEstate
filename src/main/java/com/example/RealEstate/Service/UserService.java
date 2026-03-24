package com.example.RealEstate.Service;

import com.example.RealEstate.Enum.Role;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Register a new user
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setVerified(false);
        return userRepository.save(user);
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Get user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get users by role (e.g. all AGENTS)
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // Update user profile
    public User updateUser(Long id, User updatedData) {
        User existing = getUserById(id);
        existing.setFullName(updatedData.getFullName());
        existing.setPhone(updatedData.getPhone());
        existing.setProfileImageUrl(updatedData.getProfileImageUrl());
        return userRepository.save(existing);
    }

    // Enable or disable a user (admin action)
    public User toggleUserStatus(Long id) {
        User user = getUserById(id);
        user.setEnabled(!user.isEnabled());
        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(Long id) {
        getUserById(id); // throws if not found
        userRepository.deleteById(id);
    }
}