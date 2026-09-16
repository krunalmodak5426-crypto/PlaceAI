package com.krunal.placeai.dto;

public class AwtAnswerResult {

    private Long questionId;

    private boolean correct;

    private String selectedAnswer;

    private String correctAnswer;

    private String solution;

    public AwtAnswerResult() {
    }

    public AwtAnswerResult(
            Long questionId,
            boolean correct,
            String selectedAnswer,
            String correctAnswer,
            String solution) {

        this.questionId = questionId;
        this.correct = correct;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
        this.solution = solution;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getSolution() {
        return solution;
    }
}