package com.krunal.placeai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krunal.placeai.entity.User;
import com.krunal.placeai.repository.UserRepository;
import com.krunal.placeai.dto.LoginResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // =========================
    // REGISTER
    // =========================

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        if (user.getPassword() == null ||
                user.getPassword().isEmpty()) {

            throw new RuntimeException("Password cannot be empty");
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        if (user.getRole() == null ||
                user.getRole().isEmpty()) {

            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // =========================
    // LOGIN
    // =========================

    public LoginResponse loginUser(
            String email,
            String password) {

        System.out.println("========== LOGIN DEBUG ==========");

        System.out.println(
                "Email received: " + email
        );

        System.out.println(
                "Password received: " +
                        (password == null ? "NULL" : "YES")
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        )
                );

        System.out.println(
                "User found: " + user.getEmail()
        );

        System.out.println(
                "Stored password exists: " +
                        (user.getPassword() != null)
        );

        boolean passwordMatches =
                passwordEncoder.matches(
                        password,
                        user.getPassword()
                );

        System.out.println(
                "Password matches: " + passwordMatches
        );

        System.out.println(
                "================================="
        );

        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        // Generate JWT token
        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse(
                "Login successful",
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                token
        );
    }
}