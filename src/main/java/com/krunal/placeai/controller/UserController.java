package com.krunal.placeai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krunal.placeai.dto.LoginRequest;
import com.krunal.placeai.dto.LoginResponse;
import com.krunal.placeai.entity.User;
import com.krunal.placeai.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        try {

            System.out.println("REGISTER API HIT");
            System.out.println("Email: " + user.getEmail());

            User savedUser = userService.registerUser(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedUser);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        try {

            System.out.println("LOGIN API HIT");
            System.out.println("Email: " + request.getEmail());

            LoginResponse response =
                    userService.loginUser(
                            request.getEmail(),
                            request.getPassword()
                    );

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }


    // =========================
    // PROFILE
    // =========================

    @GetMapping("/profile")
    public ResponseEntity<String> profile() {

        return ResponseEntity.ok(
                "Welcome! You are logged in."
        );
    }
}