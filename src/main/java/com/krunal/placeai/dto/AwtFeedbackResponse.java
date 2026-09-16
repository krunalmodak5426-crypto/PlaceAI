package com.krunal.placeai.dto;

public class AwtFeedbackResponse {

    private int score;
    private int totalQuestions;
    private double percentage;

    private String performance;
    private String strengths;
    private String improvement;

    public AwtFeedbackResponse() {
    }

    public AwtFeedbackResponse(
            int score,
            int totalQuestions,
            double percentage,
            String performance,
            String strengths,
            String improvement) {

        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.performance = performance;
        this.strengths = strengths;
        this.improvement = improvement;
    }

    // Constructor required by AwtTestService
    public AwtFeedbackResponse(
            String performance,
            String strengths,
            String improvement) {

        this.performance = performance;
        this.strengths = strengths;
        this.improvement = improvement;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getImprovement() {
        return improvement;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }
}