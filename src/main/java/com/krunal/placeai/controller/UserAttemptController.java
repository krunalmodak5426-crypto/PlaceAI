package com.krunal.placeai.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krunal.placeai.entity.User;
import com.krunal.placeai.entity.UserAttempt;
import com.krunal.placeai.repository.UserAttemptRepository;
import com.krunal.placeai.repository.UserRepository;

@RestController
@RequestMapping("/api/user-attempts")
public class UserAttemptController {

    private final UserRepository userRepository;
    private final UserAttemptRepository userAttemptRepository;

    public UserAttemptController(
            UserRepository userRepository,
            UserAttemptRepository userAttemptRepository) {

        this.userRepository = userRepository;
        this.userAttemptRepository = userAttemptRepository;
    }

    /* ==================================================
       SAVE ATTEMPT
       ================================================== */

    @PostMapping
    public ResponseEntity<?> saveAttempt(
            @RequestBody AttemptRequest request) {

        if (request.getEmail() == null ||
            request.getEmail().trim().isEmpty()) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "success", false,
                            "message", "User email is required."
                    )
            );
        }

        Optional<User> optionalUser =
                userRepository.findByEmail(
                        request.getEmail()
                );

        if (optionalUser.isEmpty()) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "success", false,
                            "message", "User not found."
                    )
            );
        }

        User user = optionalUser.get();

        UserAttempt attempt = new UserAttempt(
                user,
                request.getType(),
                request.getItemId(),
                request.getStatus()
        );

        UserAttempt saved =
                userAttemptRepository.save(attempt);

        Map<String, Object> response =
                new HashMap<>();

        response.put("success", true);
        response.put("attemptId", saved.getId());
        response.put(
                "message",
                "Attempt saved successfully."
        );

        return ResponseEntity.ok(response);
    }


    /* ==================================================
       GET USER SUMMARY
       ================================================== */

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(
            @RequestParam String email) {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "success", false,
                            "message", "User not found."
                    )
            );
        }

        User user = optionalUser.get();

        Long userId = user.getId();


        /* ================= APTITUDE ================= */

        long aptitudeAttempted =
                userAttemptRepository
                        .countByUserIdAndType(
                                userId,
                                "APTITUDE"
                        );

        long aptitudeCorrect =
                userAttemptRepository
                        .countByUserIdAndTypeAndStatus(
                                userId,
                                "APTITUDE",
                                "CORRECT"
                        );

        long aptitudeWrong =
                userAttemptRepository
                        .countByUserIdAndTypeAndStatus(
                                userId,
                                "APTITUDE",
                                "WRONG"
                        );


        double aptitudeAccuracy = 0;

        if (aptitudeAttempted > 0) {

            aptitudeAccuracy =
                    Math.round(
                            (
                                    (double) aptitudeCorrect
                                    / aptitudeAttempted
                            ) * 1000
                    ) / 10.0;

        }


        /* ================= CODING ================= */

        long codingAttempted =
                userAttemptRepository
                        .countByUserIdAndType(
                                userId,
                                "CODING"
                        );

        long codingPassed =
                userAttemptRepository
                        .countByUserIdAndTypeAndStatus(
                                userId,
                                "CODING",
                                "PASSED"
                        );

        long codingFailed =
                userAttemptRepository
                        .countByUserIdAndTypeAndStatus(
                                userId,
                                "CODING",
                                "FAILED"
                        );


        double codingSuccessRate = 0;

        if (codingAttempted > 0) {

            codingSuccessRate =
                    Math.round(
                            (
                                    (double) codingPassed
                                    / codingAttempted
                            ) * 1000
                    ) / 10.0;

        }


        /* ================= RESPONSE ================= */

        Map<String, Object> response =
                new HashMap<>();

        response.put("success", true);

        response.put(
                "fullName",
                user.getFullName()
        );

        response.put(
                "email",
                user.getEmail()
        );


        response.put(
                "aptitudeAttempted",
                aptitudeAttempted
        );

        response.put(
                "aptitudeCorrect",
                aptitudeCorrect
        );

        response.put(
                "aptitudeWrong",
                aptitudeWrong
        );

        response.put(
                "aptitudeAccuracy",
                aptitudeAccuracy
        );


        response.put(
                "codingAttempted",
                codingAttempted
        );

        response.put(
                "codingPassed",
                codingPassed
        );

        response.put(
                "codingFailed",
                codingFailed
        );

        response.put(
                "codingSuccessRate",
                codingSuccessRate
        );


        return ResponseEntity.ok(response);
    }


    /* ==================================================
       REQUEST DTO
       ================================================== */

    public static class AttemptRequest {

        private String email;

        private String type;

        private Long itemId;

        private String status;


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}