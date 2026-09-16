package com.krunal.placeai.dto;

public class AwtScoreSummaryResponse {

    private int totalTests;
    private double bestScore;
    private double averageScore;
    private int totalCorrectAnswers;

    public AwtScoreSummaryResponse() {
    }

    public AwtScoreSummaryResponse(
            int totalTests,
            double bestScore,
            double averageScore,
            int totalCorrectAnswers) {

        this.totalTests = totalTests;
        this.bestScore = bestScore;
        this.averageScore = averageScore;
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public double getBestScore() {
        return bestScore;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }
}