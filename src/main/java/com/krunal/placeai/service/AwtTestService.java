package com.krunal.placeai.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.krunal.placeai.dto.AwtAnswerRequest;
import com.krunal.placeai.dto.AwtFeedbackResponse;
import com.krunal.placeai.dto.AwtQuestionResponse;
import com.krunal.placeai.dto.AwtResultResponse;
import com.krunal.placeai.dto.AwtScoreSummaryResponse;
import com.krunal.placeai.entity.AwtQuestion;
import com.krunal.placeai.entity.AwtResult;
import com.krunal.placeai.entity.User;
import com.krunal.placeai.repository.AwtQuestionRepository;
import com.krunal.placeai.repository.AwtResultRepository;
import com.krunal.placeai.repository.UserRepository;

@Service
public class AwtTestService {

    @Autowired
    private AwtQuestionRepository awtQuestionRepository;

    @Autowired
    private AwtResultRepository awtResultRepository;

    @Autowired
    private UserRepository userRepository;


    // =====================================================
    // GET LOGGED-IN USER
    // =====================================================

    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
            !authentication.isAuthenticated()) {

            throw new RuntimeException(
                    "User is not authenticated."
            );
        }

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found: " + email
                        )
                );
    }


    // =====================================================
    // START TEST
    // =====================================================

    public List<AwtQuestionResponse> generateTest() {

        List<AwtQuestion> allQuestions =
                new ArrayList<>(
                        awtQuestionRepository.findAll()
                );

        if (allQuestions.size() < 20) {

            throw new RuntimeException(
                    "Not enough questions. At least 20 questions are required."
            );
        }

        Collections.shuffle(allQuestions);

        List<AwtQuestion> selectedQuestions =
                allQuestions.subList(0, 20);

        List<AwtQuestionResponse> response =
                new ArrayList<>();

        for (AwtQuestion question : selectedQuestions) {

            response.add(
                    new AwtQuestionResponse(
                            question.getId(),
                            question.getQuestion(),
                            question.getOptionA(),
                            question.getOptionB(),
                            question.getOptionC(),
                            question.getOptionD(),
                            question.getTopic(),
                            question.getDifficulty()
                    )
            );
        }

        return response;
    }


    // =====================================================
    // SUBMIT TEST
    // =====================================================

    public AwtResultResponse submitTest(
            List<AwtAnswerRequest> answers) {

        if (answers == null || answers.isEmpty()) {

            throw new RuntimeException(
                    "No answers were submitted."
            );
        }

        User student = getLoggedInUser();

        int correctAnswers = 0;

        for (AwtAnswerRequest answer : answers) {

            if (answer.getQuestionId() == null) {
                continue;
            }

            if (answer.getAnswer() == null ||
                answer.getAnswer().trim().isEmpty()) {

                continue;
            }

            AwtQuestion question =
                    awtQuestionRepository.findById(
                            answer.getQuestionId()
                    ).orElse(null);

            if (question == null) {
                continue;
            }

            String correctAnswer =
                    question.getCorrectAnswer();

            if (correctAnswer != null &&
                correctAnswer.equalsIgnoreCase(
                        answer.getAnswer().trim()
                )) {

                correctAnswers++;
            }
        }

        int totalQuestions = answers.size();

        int wrongAnswers =
                totalQuestions - correctAnswers;

        double percentage =
                ((double) correctAnswers /
                totalQuestions) * 100;


        // =================================================
        // SAVE RESULT
        // =================================================

        AwtResult result = new AwtResult();

        result.setStudent(student);
        result.setScore(correctAnswers);
        result.setTotalQuestions(totalQuestions);
        result.setCorrectAnswers(correctAnswers);
        result.setWrongAnswers(wrongAnswers);
        result.setPercentage(percentage);
        result.setTestDate(LocalDateTime.now());

        awtResultRepository.save(result);


        return new AwtResultResponse(
                correctAnswers,
                totalQuestions,
                correctAnswers,
                wrongAnswers,
                percentage
        );
    }


    // =====================================================
    // MY RESULTS
    // =====================================================

    public List<AwtResultResponse> getMyResults() {

        User student = getLoggedInUser();

        List<AwtResult> results =
                awtResultRepository.findByStudent(student);

        List<AwtResultResponse> response =
                new ArrayList<>();

        for (AwtResult result : results) {

            response.add(
                    new AwtResultResponse(
                            result.getScore(),
                            result.getTotalQuestions(),
                            result.getCorrectAnswers(),
                            result.getWrongAnswers(),
                            result.getPercentage()
                    )
            );
        }

        return response;
    }


    // =====================================================
    // SCORE SUMMARY
    // =====================================================

    public AwtScoreSummaryResponse getMyScore() {

        User student = getLoggedInUser();

        List<AwtResult> results =
                awtResultRepository.findByStudent(student);

        if (results.isEmpty()) {

            return new AwtScoreSummaryResponse(
                    0,
                    0,
                    0,
                    0
            );
        }

        int totalTests = results.size();

        double bestPercentage = 0;
        double totalPercentage = 0;

        int totalCorrect = 0;
        int totalQuestions = 0;

        for (AwtResult result : results) {

            if (result.getPercentage() > bestPercentage) {
                bestPercentage = result.getPercentage();
            }

            totalPercentage += result.getPercentage();

            totalCorrect += result.getCorrectAnswers();

            totalQuestions += result.getTotalQuestions();
        }

        double averagePercentage =
                totalPercentage / totalTests;

        return new AwtScoreSummaryResponse(
                totalTests,
                Math.round(bestPercentage * 100.0) / 100.0,
                Math.round(averagePercentage * 100.0) / 100.0,
                totalCorrect
        );
    }


    // =====================================================
    // FEEDBACK
    // =====================================================

    public AwtFeedbackResponse getMyFeedback() {

        User student = getLoggedInUser();

        List<AwtResult> results =
                awtResultRepository.findByStudent(student);

        if (results.isEmpty()) {

            return new AwtFeedbackResponse(
                    "No feedback available yet.",
                    "Take your first AWT test.",
                    "No recommendation available."
            );
        }

        AwtResult latest =
                results.get(results.size() - 1);

        double percentage =
                latest.getPercentage();

        String performance;
        String feedback;
        String recommendation;


        if (percentage >= 80) {

            performance = "Excellent";

            feedback =
                    "Your AWT performance is excellent. "
                    + "You have demonstrated strong aptitude "
                    + "and problem-solving ability.";

            recommendation =
                    "Continue practicing medium and hard questions "
                    + "and focus on maintaining your accuracy.";

        } else if (percentage >= 60) {

            performance = "Good";

            feedback =
                    "Your performance is good, but there is "
                    + "room for improvement.";

            recommendation =
                    "Practice more questions and review the "
                    + "topics where you make mistakes.";

        } else if (percentage >= 40) {

            performance = "Average";

            feedback =
                    "Your fundamentals are developing, but "
                    + "your accuracy needs improvement.";

            recommendation =
                    "Strengthen your basic concepts and practice "
                    + "easy and medium questions regularly.";

        } else {

            performance = "Needs Improvement";

            feedback =
                    "Your current score indicates that your "
                    + "fundamentals need more practice.";

            recommendation =
                    "Start with easy questions, revise the basics, "
                    + "and gradually move toward medium difficulty.";
        }


        return new AwtFeedbackResponse(
                performance,
                feedback,
                recommendation
        );
    }
}