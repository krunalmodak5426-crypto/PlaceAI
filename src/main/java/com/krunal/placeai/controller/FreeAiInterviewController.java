package com.krunal.placeai.controller;

import com.krunal.placeai.service.FreeAiInterviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/free-ai-interview")
@CrossOrigin
public class FreeAiInterviewController {

    private final FreeAiInterviewService interviewService;


    public FreeAiInterviewController(
            FreeAiInterviewService interviewService
    ) {

        this.interviewService =
                interviewService;
    }


    /* =====================================================
       START INTERVIEW
       ===================================================== */

    @PostMapping("/start")
    public ResponseEntity<?> startInterview(
            @RequestBody Map<String, Object> request
    ) {

        try {

            String language =
                    String.valueOf(
                            request.getOrDefault(
                                    "language",
                                    ""
                            )
                    );


            String difficulty =
                    String.valueOf(
                            request.getOrDefault(
                                    "difficulty",
                                    ""
                            )
                    );


            String topic =
                    String.valueOf(
                            request.getOrDefault(
                                    "topic",
                                    ""
                            )
                    );


            if (
                    language.isBlank() ||
                    difficulty.isBlank() ||
                    topic.isBlank()
            ) {

                return ResponseEntity
                        .badRequest()
                        .body(
                                Map.of(
                                        "message",
                                        "Language, difficulty and topic are required."
                                )
                        );

            }


            Map<String, Object> result =
                    interviewService.startInterview(
                            language,
                            difficulty,
                            topic
                    );


            return ResponseEntity.ok(
                    result
            );

        }

        catch (Exception e) {

            e.printStackTrace();


            return ResponseEntity
                    .internalServerError()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );

        }

    }


    /* =====================================================
       ANSWER
       ===================================================== */

    @PostMapping("/answer")
    public ResponseEntity<?> answer(
            @RequestBody Map<String, Object> request
    ) {

        try {

            String language =
                    String.valueOf(
                            request.getOrDefault(
                                    "language",
                                    ""
                            )
                    );


            String difficulty =
                    String.valueOf(
                            request.getOrDefault(
                                    "difficulty",
                                    ""
                            )
                    );


            String topic =
                    String.valueOf(
                            request.getOrDefault(
                                    "topic",
                                    ""
                            )
                    );


            String question =
                    String.valueOf(
                            request.getOrDefault(
                                    "question",
                                    ""
                            )
                    );


            String answer =
                    String.valueOf(
                            request.getOrDefault(
                                    "answer",
                                    ""
                            )
                    );


            int questionNumber =
                    parseInteger(
                            request.get(
                                    "questionNumber"
                            )
                    );


            if (
                    language.isBlank() ||
                    difficulty.isBlank() ||
                    topic.isBlank() ||
                    question.isBlank() ||
                    answer.isBlank()
            ) {

                return ResponseEntity
                        .badRequest()
                        .body(
                                Map.of(
                                        "message",
                                        "Language, difficulty, topic, question and answer are required."
                                )
                        );

            }


            Map<String, Object> result =
                    interviewService.evaluateAnswer(
                            language,
                            difficulty,
                            topic,
                            questionNumber,
                            question,
                            answer
                    );


            return ResponseEntity.ok(
                    result
            );

        }

        catch (Exception e) {

            e.printStackTrace();


            return ResponseEntity
                    .internalServerError()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );

        }

    }


    /* =====================================================
       FINAL
       ===================================================== */

    @PostMapping("/final")
    public ResponseEntity<?> finalInterview(
            @RequestBody Map<String, Object> request
    ) {

        try {

            String language =
                    String.valueOf(
                            request.getOrDefault(
                                    "language",
                                    ""
                            )
                    );


            String difficulty =
                    String.valueOf(
                            request.getOrDefault(
                                    "difficulty",
                                    ""
                            )
                    );


            String topic =
                    String.valueOf(
                            request.getOrDefault(
                                    "topic",
                                    ""
                            )
                    );


            Object attemptsObject =
                    request.get(
                            "attempts"
                    );


            List<Map<String, Object>> attempts =
                    List.of();


            if (
                    attemptsObject instanceof List<?>
            ) {

                @SuppressWarnings("unchecked")
                List<Map<String, Object>> temp =
                        (List<Map<String, Object>>)
                                attemptsObject;

                attempts =
                        temp;

            }


            Map<String, Object> result =
                    interviewService.finalFeedback(
                            language,
                            difficulty,
                            topic,
                            attempts
                    );


            return ResponseEntity.ok(
                    result
            );

        }

        catch (Exception e) {

            e.printStackTrace();


            return ResponseEntity
                    .internalServerError()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );

        }

    }


    /* =====================================================
       INTEGER PARSER
       ===================================================== */

    private int parseInteger(
            Object value
    ) {

        if (value == null) {

            return 1;

        }


        try {

            return Integer.parseInt(
                    String.valueOf(value)
            );

        }

        catch (Exception e) {

            return 1;

        }

    }

}