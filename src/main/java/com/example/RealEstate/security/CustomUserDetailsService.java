package com.example.RealEstate.security;


import com.example.RealEstate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.RealEstate.Model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username.toLowerCase())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is disabled");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(
                        "ROLE_" + user.getRole().name()
                ))
        );
    }
}

