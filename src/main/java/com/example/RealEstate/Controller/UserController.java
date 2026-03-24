package com.example.RealEstate.Controller;

import com.example.RealEstate.Enum.Role;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST /api/users/register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User saved = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // GET /api/users
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /api/users/role/{role}
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getByRole(@PathVariable Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // PATCH /api/users/{id}/toggle-status
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<User> toggleStatus(@PathVariable Long id) {
        return ResponseEntity.ok(userService.toggleUserStatus(id));
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}