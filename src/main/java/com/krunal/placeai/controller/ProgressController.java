package com.krunal.placeai.controller;

import com.krunal.placeai.entity.AwtQuestion;
import com.krunal.placeai.entity.CodingProblem;
import com.krunal.placeai.entity.User;
import com.krunal.placeai.entity.UserAttempt;

import com.krunal.placeai.repository.AwtQuestionRepository;
import com.krunal.placeai.repository.CodingProblemRepository;
import com.krunal.placeai.repository.UserAttemptRepository;
import com.krunal.placeai.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final UserRepository userRepository;
    private final UserAttemptRepository userAttemptRepository;
    private final AwtQuestionRepository awtQuestionRepository;
    private final CodingProblemRepository codingProblemRepository;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public ProgressController(
            UserRepository userRepository,
            UserAttemptRepository userAttemptRepository,
            AwtQuestionRepository awtQuestionRepository,
            CodingProblemRepository codingProblemRepository
    ) {
        this.userRepository = userRepository;
        this.userAttemptRepository = userAttemptRepository;
        this.awtQuestionRepository = awtQuestionRepository;
        this.codingProblemRepository = codingProblemRepository;
    }


    /* ==================================================
       COMPLETE PROGRESS
       ================================================== */

    @GetMapping
    public ResponseEntity<?> getProgress(
            @RequestParam String email
    ) {

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

        List<UserAttempt> attempts =
                userAttemptRepository
                        .findByUserIdOrderByAttemptedAtDesc(
                                user.getId()
                        );


        /* ==================================================
           APTITUDE SUMMARY
           ================================================== */

        int aptitudeTotal = 0;
        int aptitudeCorrect = 0;
        int aptitudeWrong = 0;


        /* Topic + Difficulty */

        Map<String, Map<String, Integer>> aptitudeTable =
                createAptitudeTable();


        /* ==================================================
           CODING SUMMARY
           ================================================== */

        int codingTotal = 0;
        int codingPassed = 0;
        int codingFailed = 0;


        /* Language + Difficulty */

        Map<String, Map<String, Integer>> codingTable =
                createCodingTable();


        /* ==================================================
           RECENT ACTIVITY
           ================================================== */

        List<Map<String, Object>> recentActivity =
                new ArrayList<>();


        for (UserAttempt attempt : attempts) {

            String type =
                    attempt.getType() == null
                    ? ""
                    : attempt.getType().toUpperCase();


            /* ==================================================
               APTITUDE
               ================================================== */

            if ("APTITUDE".equals(type)) {

                aptitudeTotal++;


                if ("CORRECT".equalsIgnoreCase(
                        attempt.getStatus())) {

                    aptitudeCorrect++;

                }


                if ("WRONG".equalsIgnoreCase(
                        attempt.getStatus())) {

                    aptitudeWrong++;

                }


                AwtQuestion q =
                        awtQuestionRepository
                                .findById(
                                        attempt.getItemId()
                                )
                                .orElse(null);


                if (q != null) {

                    String topic =
                            normalizeTopic(q.getTopic());

                    String difficulty =
                            normalizeDifficulty(
                                    q.getDifficulty()
                            );


                    increment(
                            aptitudeTable,
                            topic,
                            difficulty
                    );


                    Map<String, Object> activity =
                            new LinkedHashMap<>();

                    activity.put(
                            "type",
                            "Aptitude"
                    );

                    activity.put(
                            "question",
                            q.getQuestion()
                    );

                    activity.put(
                            "topic",
                            topic
                    );

                    activity.put(
                            "difficulty",
                            difficulty
                    );

                    activity.put(
                            "status",
                            attempt.getStatus()
                    );

                    activity.put(
                            "dateTime",
                            attempt.getAttemptedAt()
                                    .format(formatter)
                    );


                    recentActivity.add(activity);

                }

            }


            /* ==================================================
               CODING
               ================================================== */

            else if ("CODING".equals(type)) {

                codingTotal++;


                if ("PASSED".equalsIgnoreCase(
                        attempt.getStatus())) {

                    codingPassed++;

                }


                if ("FAILED".equalsIgnoreCase(
                        attempt.getStatus())) {

                    codingFailed++;

                }


                CodingProblem problem =
                        codingProblemRepository
                                .findById(
                                        attempt.getItemId()
                                )
                                .orElse(null);


                if (problem != null) {

                    String language =
                            normalizeLanguage(
                                    problem.getLanguage()
                            );

                    String difficulty =
                            normalizeDifficulty(
                                    problem.getDifficulty()
                            );


                    increment(
                            codingTable,
                            language,
                            difficulty
                    );


                    Map<String, Object> activity =
                            new LinkedHashMap<>();

                    activity.put(
                            "type",
                            "Coding"
                    );

                    activity.put(
                            "question",
                            problem.getTitle()
                    );

                    activity.put(
                            "topic",
                            language
                    );

                    activity.put(
                            "difficulty",
                            difficulty
                    );

                    activity.put(
                            "status",
                            attempt.getStatus()
                    );

                    activity.put(
                            "dateTime",
                            attempt.getAttemptedAt()
                                    .format(formatter)
                    );


                    recentActivity.add(activity);

                }

            }

        }


        /* ==================================================
           ACCURACY
           ================================================== */

        double aptitudeAccuracy = 0;

        if (aptitudeTotal > 0) {

            aptitudeAccuracy =
                    roundOneDecimal(
                            (
                                    (double) aptitudeCorrect
                                    / aptitudeTotal
                            ) * 100
                    );

        }


        double codingSuccessRate = 0;

        if (codingTotal > 0) {

            codingSuccessRate =
                    roundOneDecimal(
                            (
                                    (double) codingPassed
                                    / codingTotal
                            ) * 100
                    );

        }


        /* ==================================================
           RESPONSE
           ================================================== */

        Map<String, Object> response =
                new LinkedHashMap<>();

        response.put(
                "success",
                true
        );

        response.put(
                "fullName",
                user.getFullName()
        );

        response.put(
                "email",
                user.getEmail()
        );


        /* Aptitude */

        response.put(
                "aptitudeTotal",
                aptitudeTotal
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
                "aptitudeTable",
                aptitudeTable
        );


        /* Coding */

        response.put(
                "codingTotal",
                codingTotal
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

        response.put(
                "codingTable",
                codingTable
        );


        /* Recent */

        response.put(
                "recentActivity",
                recentActivity
        );


        return ResponseEntity.ok(response);
    }


    /* ==================================================
       APTITUDE TABLE
       ================================================== */

    private Map<String, Map<String, Integer>>
    createAptitudeTable() {

        Map<String, Map<String, Integer>> table =
                new LinkedHashMap<>();

        table.put(
                "Quantitative",
                createDifficultyMap()
        );

        table.put(
                "Logical",
                createDifficultyMap()
        );

        table.put(
                "Verbal",
                createDifficultyMap()
        );

        table.put(
                "Grammar",
                createDifficultyMap()
        );

        table.put(
                "Technical",
                createDifficultyMap()
        );

        return table;
    }


    /* ==================================================
       CODING TABLE
       ================================================== */

    private Map<String, Map<String, Integer>>
    createCodingTable() {

        Map<String, Map<String, Integer>> table =
                new LinkedHashMap<>();

        table.put(
                "Java",
                createDifficultyMap()
        );

        table.put(
                "Python",
                createDifficultyMap()
        );

        table.put(
                "C++",
                createDifficultyMap()
        );

        return table;
    }


    /* ==================================================
       DIFFICULTY MAP
       ================================================== */

    private Map<String, Integer>
    createDifficultyMap() {

        Map<String, Integer> map =
                new LinkedHashMap<>();

        map.put("Easy", 0);
        map.put("Medium", 0);
        map.put("Hard", 0);

        return map;
    }


    /* ==================================================
       INCREMENT
       ================================================== */

    private void increment(
            Map<String, Map<String, Integer>> table,
            String row,
            String difficulty
    ) {

        if (!table.containsKey(row)) {

            table.put(
                    row,
                    createDifficultyMap()
            );

        }


        Map<String, Integer> difficultyMap =
                table.get(row);


        if (!difficultyMap.containsKey(difficulty)) {

            difficultyMap.put(
                    difficulty,
                    0
            );

        }


        difficultyMap.put(
                difficulty,
                difficultyMap.get(difficulty) + 1
        );
    }


    /* ==================================================
       NORMALIZE TOPIC
       ================================================== */

    private String normalizeTopic(String topic) {

        if (topic == null ||
            topic.trim().isEmpty()) {

            return "Other";

        }

        String value =
                topic.trim().toLowerCase();


        if (value.contains("quant")) {
            return "Quantitative";
        }

        if (value.contains("logical") ||
            value.contains("reason")) {

            return "Logical";
        }

        if (value.contains("verbal")) {
            return "Verbal";
        }

        if (value.contains("grammar")) {
            return "Grammar";
        }

        if (value.contains("technical")) {
            return "Technical";
        }


        return capitalize(topic);
    }


    /* ==================================================
       NORMALIZE DIFFICULTY
       ================================================== */

    private String normalizeDifficulty(
            String difficulty
    ) {

        if (difficulty == null ||
            difficulty.trim().isEmpty()) {

            return "Other";

        }


        String value =
                difficulty.trim().toLowerCase();


        if (value.equals("easy")) {
            return "Easy";
        }

        if (value.equals("medium") ||
            value.equals("moderate")) {

            return "Medium";
        }

        if (value.equals("hard") ||
            value.equals("difficult")) {

            return "Hard";
        }


        return capitalize(difficulty);
    }


    /* ==================================================
       NORMALIZE LANGUAGE
       ================================================== */

    private String normalizeLanguage(
            String language
    ) {

        if (language == null ||
            language.trim().isEmpty()) {

            return "Other";

        }


        String value =
                language.trim().toLowerCase();


        if (value.equals("java")) {
            return "Java";
        }

        if (value.equals("python")) {
            return "Python";
        }

        if (value.equals("c++") ||
            value.equals("cpp")) {

            return "C++";
        }


        return capitalize(language);
    }


    /* ==================================================
       CAPITALIZE
       ================================================== */

    private String capitalize(String value) {

        if (value == null ||
            value.isEmpty()) {

            return "Other";

        }

        return value.substring(0, 1).toUpperCase()
                + value.substring(1).toLowerCase();
    }


    /* ==================================================
       ROUND
       ================================================== */

    private double roundOneDecimal(double value) {

        return Math.round(value * 10.0) / 10.0;
    }

}