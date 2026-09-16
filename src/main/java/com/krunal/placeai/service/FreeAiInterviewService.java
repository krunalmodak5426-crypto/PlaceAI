package com.krunal.placeai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FreeAiInterviewService {

    /*
     * Gemini model.
     */
    private static final String MODEL =
            "gemini-3.1-flash-lite";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;


    /* =====================================================
       CONSTRUCTOR
       ===================================================== */

    public FreeAiInterviewService() {

        httpClient =
                HttpClient.newHttpClient();

        objectMapper =
                new ObjectMapper();
    }


    /* =====================================================
       GET API KEY
       ===================================================== */

    private String getApiKey() {

        String apiKey =
                System.getenv("GEMINI_API_KEY");


        if (
                apiKey == null ||
                apiKey.isBlank()
        ) {

            throw new RuntimeException(
                    "GEMINI_API_KEY environment variable is not set."
            );

        }


        return apiKey.trim();
    }


    /* =====================================================
       CALL GEMINI
       ===================================================== */

    private String callGemini(
            String prompt
    ) {

        try {

            String apiKey =
                    getApiKey();


            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/"
                    + MODEL
                    + ":generateContent";


            Map<String, Object> part =
                    new HashMap<>();


            part.put(
                    "text",
                    prompt
            );


            Map<String, Object> content =
                    new HashMap<>();


            content.put(
                    "parts",
                    List.of(part)
            );


            Map<String, Object> requestBody =
                    new HashMap<>();


            requestBody.put(
                    "contents",
                    List.of(content)
            );


            String jsonBody =
                    objectMapper.writeValueAsString(
                            requestBody
                    );


            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "GEMINI REQUEST MODEL: " +
                    MODEL
            );

            System.out.println(
                    "GEMINI URL: " +
                    url
            );


            HttpRequest request =
                    HttpRequest.newBuilder()

                            .uri(
                                    URI.create(url)
                            )

                            .header(
                                    "Content-Type",
                                    "application/json"
                            )

                            .header(
                                    "x-goog-api-key",
                                    apiKey
                            )

                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(jsonBody)
                            )

                            .build();


            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            System.out.println(
                    "GEMINI STATUS: " +
                    response.statusCode()
            );


            System.out.println(
                    "GEMINI RESPONSE:"
            );

            System.out.println(
                    response.body()
            );

            System.out.println(
                    "========================================"
            );


            if (
                    response.statusCode() < 200 ||
                    response.statusCode() >= 300
            ) {

                throw new RuntimeException(
                        "Gemini API error. HTTP "
                        + response.statusCode()
                        + " | "
                        + response.body()
                );

            }


            JsonNode root =
                    objectMapper.readTree(
                            response.body()
                    );


            JsonNode candidates =
                    root.path(
                            "candidates"
                    );


            if (
                    !candidates.isArray() ||
                    candidates.size() == 0
            ) {

                throw new RuntimeException(
                        "Gemini returned no candidates. Response: "
                        + response.body()
                );

            }


            JsonNode contentNode =
                    candidates
                            .get(0)
                            .path("content");


            JsonNode parts =
                    contentNode
                            .path("parts");


            if (
                    !parts.isArray() ||
                    parts.size() == 0
            ) {

                throw new RuntimeException(
                        "Gemini returned no text content. Response: "
                        + response.body()
                );

            }


            String text =
                    parts
                            .get(0)
                            .path("text")
                            .asText();


            if (
                    text == null ||
                    text.isBlank()
            ) {

                throw new RuntimeException(
                        "Gemini returned blank text."
                );

            }


            return text.trim();

        }

        catch (RuntimeException e) {

            throw e;

        }

        catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Unable to contact Gemini: "
                    + e.getMessage(),
                    e
            );

        }

    }


    /* =====================================================
       CLEAN JSON
       ===================================================== */

    private String cleanJson(
            String text
    ) {

        if (
                text == null ||
                text.isBlank()
        ) {

            return "{}";

        }


        String cleaned =
                text.trim();


        if (
                cleaned.startsWith("```")
        ) {

            int newline =
                    cleaned.indexOf("\n");


            if (newline >= 0) {

                cleaned =
                        cleaned.substring(
                                newline + 1
                        );

            }


            if (
                    cleaned.endsWith("```")
            ) {

                cleaned =
                        cleaned.substring(
                                0,
                                cleaned.length() - 3
                        );

            }

        }


        cleaned =
                cleaned.trim();


        int firstBrace =
                cleaned.indexOf("{");


        int lastBrace =
                cleaned.lastIndexOf("}");


        if (
                firstBrace >= 0 &&
                lastBrace > firstBrace
        ) {

            cleaned =
                    cleaned.substring(
                            firstBrace,
                            lastBrace + 1
                    );

        }


        return cleaned.trim();

    }


    /* =====================================================
       START INTERVIEW
       ===================================================== */

    public Map<String, Object> startInterview(
            String language,
            String difficulty,
            String topic
    ) {

        String prompt = """

                You are the technical interviewer for PlaceAI.

                Start a technical mock interview.

                Programming Language: %s
                Difficulty: %s
                Topic: %s

                VERY IMPORTANT INTERVIEW RULES:

                1. Ask ONLY ONE question.
                2. The question must be SHORT and CLEAR.
                3. Ask only one thing at a time.
                4. Do NOT create a long problem statement.
                5. Do NOT combine multiple requirements in one question.
                6. Do NOT ask for code + explanation + time complexity +
                   space complexity + edge cases all together.
                7. Do NOT give the answer.
                8. Do NOT ask the candidate to choose language, difficulty
                   or topic.
                9. Stay strictly within the selected topic.

                DIFFICULTY RULES:

                EASY:
                - Only basic and simple questions.
                - Ask definitions, basic concepts, simple syntax,
                  simple examples and very easy problems.
                - Do NOT ask advanced algorithms.
                - Do NOT ask tricky optimization questions.
                - The question should be easy for a beginner.

                MEDIUM:
                - Ask moderate technical questions.
                - Ask conceptual questions that need some thinking.
                - Ask small coding or problem-solving questions.
                - Do not suddenly ask a very advanced problem.

                HARD:
                - Ask advanced technical questions.
                - Ask challenging problem-solving or algorithm questions.
                - Advanced concepts and optimization are allowed.
                - Questions may require deeper reasoning.

                QUESTION STYLE:

                - Maximum 1-2 short sentences.
                - Natural interview style.
                - One clear question only.
                - No unnecessary background story.
                - No multiple sub-questions.

                Examples for EASY:

                "What is an array?"
                "What is the difference between int and double?"
                "What is a loop in Java?"

                Examples for MEDIUM:

                "What is the difference between an array and an ArrayList?"
                "How would you find duplicate elements in an array?"

                Examples for HARD:

                "How would you find the longest subarray with sum K in O(n) time?"
                "How would you optimize an O(n²) solution?"

                Return ONLY valid JSON.

                Return exactly:

                {
                  "question": "your short single question"
                }

                Generate the FIRST question now.

                """.formatted(
                language,
                difficulty,
                topic
        );


        String response =
                callGemini(
                        prompt
                );


        try {

            JsonNode json =
                    objectMapper.readTree(
                            cleanJson(response)
                    );


            String question =
                    json
                            .path("question")
                            .asText();


            if (
                    question == null ||
                    question.isBlank()
            ) {

                throw new RuntimeException(
                        "Gemini did not generate a question. Raw response: "
                        + response
                );

            }


            Map<String, Object> result =
                    new HashMap<>();


            result.put(
                    "question",
                    question
            );


            return result;

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Invalid Gemini start response: "
                    + e.getMessage(),
                    e
            );

        }

    }


    /* =====================================================
       EVALUATE ANSWER
       ===================================================== */

    public Map<String, Object> evaluateAnswer(
            String language,
            String difficulty,
            String topic,
            int questionNumber,
            String question,
            String answer
    ) {

        boolean lastQuestion =
                questionNumber >= 5;


        String instruction =
                lastQuestion

                        ? """
                          This was question 5.
                          Do NOT generate another question.
                          Set finished to true.
                          """

                        : """
                          Generate exactly ONE short next technical question.
                          It must follow the selected difficulty.
                          Do NOT generate multiple questions.
                          Do NOT generate a long problem statement.
                          """;


        String prompt = """

                You are evaluating a candidate in a technical
                mock interview for PlaceAI.

                Programming Language: %s
                Difficulty: %s
                Topic: %s

                Question Number: %d

                Question:
                %s

                Candidate Answer:
                %s

                Evaluate the candidate on:

                1. Technical correctness
                2. Understanding
                3. Relevance
                4. Clarity
                5. Completeness

                Give a score from 0 to 10.

                Give SHORT and useful feedback.

                DIFFICULTY RULES:

                EASY:
                - Keep the next question basic and simple.
                - Do not suddenly ask medium or hard questions.

                MEDIUM:
                - Keep the next question moderate.
                - Use some reasoning or a small coding problem.

                HARD:
                - Keep the next question challenging.
                - Advanced concepts and problem solving are allowed.

                STRICT NEXT QUESTION RULES:

                - Ask exactly ONE question.
                - Maximum 1-2 short sentences.
                - Keep it natural and conversational.
                - Do not combine multiple questions.
                - Do not ask for code + explanation +
                  time complexity + space complexity together.
                - Do not write a huge coding problem statement.
                - Do not suddenly change the topic.
                - Do not change the selected difficulty.

                %s

                Return ONLY valid JSON.

                Return exactly:

                {
                  "score": 0,
                  "feedback": "short feedback",
                  "nextQuestion": "",
                  "finished": false
                }

                """.formatted(
                language,
                difficulty,
                topic,
                questionNumber,
                question,
                answer,
                instruction
        );


        String response =
                callGemini(
                        prompt
                );


        try {

            JsonNode json =
                    objectMapper.readTree(
                            cleanJson(response)
                    );


            int score =
                    json
                            .path("score")
                            .asInt(0);


            score =
                    Math.max(
                            0,
                            Math.min(
                                    10,
                                    score
                            )
                    );


            String feedback =
                    json
                            .path("feedback")
                            .asText(
                                    "Keep improving your technical explanation."
                            );


            String nextQuestion =
                    json
                            .path("nextQuestion")
                            .asText("");


            boolean finalFlag =
                    lastQuestion ||
                    json
                            .path("finished")
                            .asBoolean(false);


            Map<String, Object> result =
                    new HashMap<>();


            result.put(
                    "score",
                    score
            );


            result.put(
                    "feedback",
                    feedback
            );


            result.put(
                    "nextQuestion",
                    nextQuestion
            );


            result.put(
                    "finished",
                    finalFlag
            );


            return result;

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Invalid Gemini evaluation response: "
                    + e.getMessage(),
                    e
            );

        }

    }


    /* =====================================================
       FINAL FEEDBACK
       ===================================================== */

    public Map<String, Object> finalFeedback(
            String language,
            String difficulty,
            String topic,
            List<Map<String, Object>> attempts
    ) {

        if (
                attempts == null ||
                attempts.isEmpty()
        ) {

            Map<String, Object> result =
                    new HashMap<>();


            result.put(
                    "score",
                    0
            );


            result.put(
                    "performance",
                    "The interview ended before any answer was submitted."
            );


            result.put(
                    "strengths",
                    List.of()
            );


            result.put(
                    "improvements",
                    List.of(
                            "Complete the interview questions.",
                            "Give clear technical explanations.",
                            "Support answers with examples."
                    )
            );


            result.put(
                    "feedback",
                    "No detailed feedback was generated because no answer was submitted."
            );


            return result;

        }


        StringBuilder interview =
                new StringBuilder();


        for (
                int i = 0;
                i < attempts.size();
                i++
        ) {

            Map<String, Object> item =
                    attempts.get(i);


            interview

                    .append("Question ")
                    .append(i + 1)
                    .append(":\n")

                    .append(
                            String.valueOf(
                                    item.getOrDefault(
                                            "question",
                                            ""
                                    )
                            )
                    )

                    .append("\n\n")

                    .append("Candidate Answer:\n")

                    .append(
                            String.valueOf(
                                    item.getOrDefault(
                                            "answer",
                                            ""
                                    )
                            )
                    )

                    .append("\n\n")

                    .append("Score:\n")

                    .append(
                            String.valueOf(
                                    item.getOrDefault(
                                            "score",
                                            0
                                    )
                            )
                    )

                    .append("/10\n\n");

        }


        String prompt = """

                You are generating the final technical interview
                report for PlaceAI.

                Programming Language: %s
                Difficulty: %s
                Topic: %s

                Interview data:

                %s

                Generate an overall score from 0 to 10.

                Analyze:

                - technical knowledge
                - correctness
                - clarity
                - problem solving
                - completeness

                Keep the final report concise and useful.

                Return ONLY valid JSON.

                Return exactly:

                {
                  "score": 0,
                  "performance": "overall performance",
                  "strengths": [
                    "strength 1",
                    "strength 2",
                    "strength 3"
                  ],
                  "improvements": [
                    "improvement 1",
                    "improvement 2",
                    "improvement 3"
                  ],
                  "feedback": "overall feedback"
                }

                """.formatted(
                language,
                difficulty,
                topic,
                interview
        );


        String response =
                callGemini(
                        prompt
                );


        try {

            JsonNode json =
                    objectMapper.readTree(
                            cleanJson(response)
                    );


            int score =
                    json
                            .path("score")
                            .asInt(0);


            score =
                    Math.max(
                            0,
                            Math.min(
                                    10,
                                    score
                            )
                    );


            String performance =
                    json
                            .path("performance")
                            .asText(
                                    "Interview completed."
                            );


            List<String> strengths =
                    readStringArray(
                            json.path(
                                    "strengths"
                            )
                    );


            List<String> improvements =
                    readStringArray(
                            json.path(
                                    "improvements"
                            )
                    );


            String feedback =
                    json
                            .path("feedback")
                            .asText(
                                    "Keep practicing technical interviews."
                            );


            Map<String, Object> result =
                    new HashMap<>();


            result.put(
                    "score",
                    score
            );


            result.put(
                    "performance",
                    performance
            );


            result.put(
                    "strengths",
                    strengths
            );


            result.put(
                    "improvements",
                    improvements
            );


            result.put(
                    "feedback",
                    feedback
            );


            return result;

        }

        catch (Exception e) {

            throw new RuntimeException(
                    "Invalid Gemini final response: "
                    + e.getMessage(),
                    e
            );

        }

    }


    /* =====================================================
       JSON ARRAY HELPER
       ===================================================== */

    private List<String> readStringArray(
            JsonNode node
    ) {

        List<String> list =
                new ArrayList<>();


        if (
                node == null ||
                !node.isArray()
        ) {

            return list;

        }


        for (
                JsonNode item :
                node
        ) {

            String value =
                    item.asText();


            if (
                    value != null &&
                    !value.isBlank()
            ) {

                list.add(
                        value.trim()
                );

            }

        }


        return list;

    }

}