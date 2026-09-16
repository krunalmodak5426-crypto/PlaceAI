package com.krunal.placeai.service;

import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class GeminiService {

    private final Client client;

    private static final String MODEL = "gemini-3.6-flash";

    public GeminiService() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {

            throw new RuntimeException(
                    "GEMINI_API_KEY environment variable is not set."
            );
        }

        client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public String evaluateAnswer(
            String question,
            String studentAnswer,
            String language,
            String difficulty) {

        String prompt = """
                You are an AI technical interviewer for a placement portal.

                Evaluate the student's coding answer.

                Programming Language:
                %s

                Difficulty:
                %s

                Coding Question:
                %s

                Student Answer:
                %s

                Evaluate:
                1. Correctness
                2. Logic
                3. Time complexity
                4. Space complexity
                5. Major mistakes

                Give a score from 0 to 1.

                Return exactly this format:

                SCORE: 0 or 1

                CORRECTNESS: Correct / Partially Correct / Incorrect

                FEEDBACK: short feedback

                TIME_COMPLEXITY: short answer

                SPACE_COMPLEXITY: short answer

                EXPLANATION: short explanation
                """.formatted(
                        language,
                        difficulty,
                        question,
                        studentAnswer
                );

        try {

            GenerateContentResponse response =
                    client.models.generateContent(
                            MODEL,
                            prompt,
                            null
                    );

            String result = response.text();

            if (result == null || result.isBlank()) {

                return "AI evaluation failed.";
            }

            return result;

        } catch (Exception e) {

            System.out.println(
                    "===================================="
            );

            System.out.println(
                    "GEMINI API ERROR:"
            );

            System.out.println(
                    e.getMessage()
            );

            System.out.println(
                    "===================================="
            );

            return "AI evaluation failed: "
                    + e.getMessage();
        }
    }
}