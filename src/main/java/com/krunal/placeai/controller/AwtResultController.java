package com.krunal.placeai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krunal.placeai.entity.AwtResult;
import com.krunal.placeai.entity.User;
import com.krunal.placeai.repository.AwtResultRepository;
import com.krunal.placeai.repository.UserRepository;

@RestController
@RequestMapping("/api/awt/results")
public class AwtResultController {

    @Autowired
    private AwtResultRepository awtResultRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my")
    public List<AwtResult> getMyResults(Authentication authentication) {

        String email = authentication.getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new RuntimeException("Student not found: " + email)
                );

        return awtResultRepository.findByStudent(student);
    }
}