package com.krunal.placeai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krunal.placeai.dto.AwtAnswerResult;
import com.krunal.placeai.dto.AwtQuestionResponse;
import com.krunal.placeai.entity.AwtQuestion;
import com.krunal.placeai.repository.AwtQuestionRepository;

@Service
public class AwtQuestionService {

    @Autowired
    private AwtQuestionRepository awtQuestionRepository;


    // ==========================================
    // ADD ONE QUESTION
    // ==========================================

    public AwtQuestion addQuestion(AwtQuestion question) {
        return awtQuestionRepository.save(question);
    }


    // ==========================================
    // ADD MULTIPLE QUESTIONS
    // ==========================================

    public List<AwtQuestion> addQuestions(
            List<AwtQuestion> questions) {

        return awtQuestionRepository.saveAll(questions);
    }


    // ==========================================
    // GET ALL QUESTIONS
    // ==========================================

    public List<AwtQuestion> getAllQuestions() {
        return awtQuestionRepository.findAll();
    }


    // ==========================================
    // GET QUESTION BY ID
    // ==========================================

    public AwtQuestion getQuestionById(Long id) {

        return awtQuestionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Question not found with id: " + id
                        )
                );
    }


    // ==========================================
    // GET QUESTIONS BY TOPIC
    // ==========================================

    public List<AwtQuestion> getQuestionsByTopic(
            String topic) {

        return awtQuestionRepository.findByTopic(topic);
    }


    // ==========================================
    // GET QUESTIONS BY DIFFICULTY
    // ==========================================

    public List<AwtQuestion> getQuestionsByDifficulty(
            String difficulty) {

        return awtQuestionRepository.findByDifficulty(difficulty);
    }


    // ==========================================
    // STUDENT QUESTIONS
    // CORRECT ANSWER HIDDEN
    // ==========================================

    public List<AwtQuestionResponse> getStudentQuestions() {

        return awtQuestionRepository.findAll()
                .stream()
                .map(question ->
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
                )
                .toList();
    }


    // ==========================================
    // CHECK ANSWER
    // ==========================================

    public AwtAnswerResult checkAnswer(
            Long questionId,
            String answer) {

        AwtQuestion question =
                getQuestionById(questionId);


        if (answer == null || answer.isBlank()) {

            throw new IllegalArgumentException(
                    "Answer cannot be empty"
            );
        }


        String selectedAnswer =
                answer.trim();


        String correctAnswer =
                question.getCorrectAnswer();


        if (correctAnswer == null ||
            correctAnswer.isBlank()) {

            throw new RuntimeException(
                    "Correct answer is not configured for question "
                    + questionId
            );
        }


        boolean correct =
                isCorrect(
                        question,
                        selectedAnswer,
                        correctAnswer
                );


        String correctAnswerText =
                getCorrectAnswerText(
                        question,
                        correctAnswer
                );


        return new AwtAnswerResult(

                question.getId(),

                correct,

                selectedAnswer,

                correctAnswerText,

                question.getSolution()
        );
    }


    // ==========================================
    // CHECK ANSWER
    // ==========================================

    private boolean isCorrect(
            AwtQuestion question,
            String selectedAnswer,
            String correctAnswer) {

        String correct =
                correctAnswer.trim();


        // ------------------------------
        // A / B / C / D
        // ------------------------------

        if (correct.equalsIgnoreCase("A")) {

            return selectedAnswer.equalsIgnoreCase(
                    question.getOptionA()
            );
        }


        if (correct.equalsIgnoreCase("B")) {

            return selectedAnswer.equalsIgnoreCase(
                    question.getOptionB()
            );
        }


        if (correct.equalsIgnoreCase("C")) {

            return selectedAnswer.equalsIgnoreCase(
                    question.getOptionC()
            );
        }


        if (correct.equalsIgnoreCase("D")) {

            return selectedAnswer.equalsIgnoreCase(
                    question.getOptionD()
            );
        }


        // ------------------------------
        // Numeric answers
        // ------------------------------

        try {

            int number =
                    Integer.parseInt(correct);


            // 0,1,2,3
            if (number >= 0 && number <= 3) {

                String option =
                        getOptionText(
                                question,
                                number
                        );

                return selectedAnswer.equalsIgnoreCase(
                        option
                );
            }


            // 1,2,3,4
            if (number >= 1 && number <= 4) {

                String option =
                        getOptionText(
                                question,
                                number - 1
                        );

                return selectedAnswer.equalsIgnoreCase(
                        option
                );
            }

        } catch (NumberFormatException ignored) {
        }


        // ------------------------------
        // Full answer text
        // ------------------------------

        return selectedAnswer.equalsIgnoreCase(
                correct
        );
    }


    // ==========================================
    // GET OPTION TEXT
    // ==========================================

    private String getOptionText(
            AwtQuestion question,
            int index) {

        switch (index) {

            case 0:
                return question.getOptionA();

            case 1:
                return question.getOptionB();

            case 2:
                return question.getOptionC();

            case 3:
                return question.getOptionD();

            default:
                throw new IllegalArgumentException(
                        "Invalid option index"
                );
        }
    }


    // ==========================================
    // GET CORRECT ANSWER TEXT
    // ==========================================

    private String getCorrectAnswerText(
            AwtQuestion question,
            String correctAnswer) {

        String answer =
                correctAnswer.trim();


        if (answer.equalsIgnoreCase("A")) {
            return question.getOptionA();
        }


        if (answer.equalsIgnoreCase("B")) {
            return question.getOptionB();
        }


        if (answer.equalsIgnoreCase("C")) {
            return question.getOptionC();
        }


        if (answer.equalsIgnoreCase("D")) {
            return question.getOptionD();
        }


        try {

            int number =
                    Integer.parseInt(answer);


            if (number >= 0 && number <= 3) {

                return getOptionText(
                        question,
                        number
                );
            }


            if (number >= 1 && number <= 4) {

                return getOptionText(
                        question,
                        number - 1
                );
            }

        } catch (NumberFormatException ignored) {
        }


        return answer;
    }


    // ==========================================
    // DELETE QUESTION
    // ==========================================

    public void deleteQuestion(Long id) {

        if (!awtQuestionRepository.existsById(id)) {

            throw new RuntimeException(
                    "Question not found"
            );
        }

        awtQuestionRepository.deleteById(id);
    }

}