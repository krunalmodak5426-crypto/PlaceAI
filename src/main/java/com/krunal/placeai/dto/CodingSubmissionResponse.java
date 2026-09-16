package com.krunal.placeai.dto;

public class CodingSubmissionResponse {

    private boolean passed;
    private String message;
    private String actualOutput;
    private String expectedOutput;
    private String error;

    public CodingSubmissionResponse() {
    }

    public CodingSubmissionResponse(
            boolean passed,
            String message,
            String actualOutput,
            String expectedOutput,
            String error) {

        this.passed = passed;
        this.message = message;
        this.actualOutput = actualOutput;
        this.expectedOutput = expectedOutput;
        this.error = error;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActualOutput() {
        return actualOutput;
    }

    public void setActualOutput(String actualOutput) {
        this.actualOutput = actualOutput;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}