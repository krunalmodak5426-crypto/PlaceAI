package com.krunal.placeai.controller;

import com.krunal.placeai.dto.AiInterviewAttemptRequest;
import com.krunal.placeai.entity.AiInterviewAttempt;
import com.krunal.placeai.entity.User;
import com.krunal.placeai.repository.AiInterviewAttemptRepository;
import com.krunal.placeai.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-interviews")
@CrossOrigin
public class AiInterviewAttemptController {

    private final AiInterviewAttemptRepository attemptRepository;

    private final UserRepository userRepository;


    /* =====================================================
       CONSTRUCTOR
       ===================================================== */

    public AiInterviewAttemptController(
            AiInterviewAttemptRepository attemptRepository,
            UserRepository userRepository
    ) {

        this.attemptRepository =
                attemptRepository;

        this.userRepository =
                userRepository;
    }


    /* =====================================================
       SAVE ONE AI INTERVIEW ATTEMPT
       ===================================================== */

    @PostMapping("/attempts")
    public ResponseEntity<?> saveAttempt(
            @RequestBody AiInterviewAttemptRequest request
    ) {

        if (
                request.getEmail() == null ||
                request.getEmail().isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Email is required"
                    );

        }


        User user =
                userRepository
                        .findByEmail(
                                request.getEmail().trim()
                        )
                        .orElse(null);


        if (user == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "User not found"
                    );

        }


        if (
                request.getInterviewId() == null ||
                request.getInterviewId().isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Interview ID is required"
                    );

        }


        if (
                request.getLanguage() == null ||
                request.getLanguage().isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Language is required"
                    );

        }


        if (
                request.getDifficulty() == null ||
                request.getDifficulty().isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Difficulty is required"
                    );

        }


        if (
                request.getTopic() == null ||
                request.getTopic().isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Topic is required"
                    );

        }


        if (
                request.getQuestion() == null ||
                request.getQuestion().isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Question is required"
                    );

        }


        if (
                request.getQuestionNumber() == null
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Question number is required"
                    );

        }


        String result =
                request.getResult();


        if (
                result == null ||
                result.isBlank()
        ) {

            result =
                    "NOT_EVALUATED";

        }


        AiInterviewAttempt attempt =
                new AiInterviewAttempt(

                        user,

                        request.getInterviewId(),

                        request.getLanguage(),

                        request.getDifficulty(),

                        request.getTopic(),

                        request.getQuestionNumber(),

                        request.getQuestion(),

                        request.getUserAnswer(),

                        result,

                        request.getScore(),

                        request.getFeedback()

                );


        AiInterviewAttempt saved =
                attemptRepository.save(
                        attempt
                );


        return ResponseEntity.ok(
                saved
        );

    }


    /* =====================================================
       GET ALL ATTEMPTS OF A USER
       ===================================================== */

    @GetMapping("/attempts")
    public ResponseEntity<?> getAttempts(
            @RequestParam String email
    ) {

        if (
                email == null ||
                email.isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Email is required"
                    );

        }


        User user =
                userRepository
                        .findByEmail(
                                email.trim()
                        )
                        .orElse(null);


        if (user == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "User not found"
                    );

        }


        List<AiInterviewAttempt> attempts =
                attemptRepository
                        .findByUserIdOrderByAttemptedAtDesc(
                                user.getId()
                        );


        return ResponseEntity.ok(
                attempts
        );

    }


    /* =====================================================
       GET ATTEMPTS OF ONE INTERVIEW
       ===================================================== */

    @GetMapping("/attempts/interview")
    public ResponseEntity<?> getInterviewAttempts(
            @RequestParam String email,
            @RequestParam String interviewId
    ) {

        if (
                email == null ||
                email.isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Email is required"
                    );

        }


        if (
                interviewId == null ||
                interviewId.isBlank()
        ) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Interview ID is required"
                    );

        }


        User user =
                userRepository
                        .findByEmail(
                                email.trim()
                        )
                        .orElse(null);


        if (user == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "User not found"
                    );

        }


        List<AiInterviewAttempt> attempts =
                attemptRepository
                        .findByUserIdAndInterviewIdOrderByQuestionNumberAsc(
                                user.getId(),
                                interviewId
                        );


        return ResponseEntity.ok(
                attempts
        );

    }

}