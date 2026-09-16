package com.krunal.placeai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coding_problems")
public class CodingProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String language;

    private String difficulty;

    private String company;

    private boolean previouslyAsked;

    @Column(length = 5000)
    private String problemStatement;

    @Column(length = 5000)
    private String inputFormat;

    @Column(length = 5000)
    private String outputFormat;

    @Column(length = 5000)
    private String sampleInput;

    @Column(length = 5000)
    private String sampleOutput;

    @Column(length = 10000)
    private String starterCode;

    @Column(length = 10000)
    private String expectedOutput;

    @Column(length = 15000)
    private String solution;


    public CodingProblem() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public String getDifficulty() {
        return difficulty;
    }


    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }


    public boolean isPreviouslyAsked() {
        return previouslyAsked;
    }


    public void setPreviouslyAsked(boolean previouslyAsked) {
        this.previouslyAsked = previouslyAsked;
    }


    public String getProblemStatement() {
        return problemStatement;
    }


    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }


    public String getInputFormat() {
        return inputFormat;
    }


    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }


    public String getOutputFormat() {
        return outputFormat;
    }


    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }


    public String getSampleInput() {
        return sampleInput;
    }


    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }


    public String getSampleOutput() {
        return sampleOutput;
    }


    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }


    public String getStarterCode() {
        return starterCode;
    }


    public void setStarterCode(String starterCode) {
        this.starterCode = starterCode;
    }


    public String getExpectedOutput() {
        return expectedOutput;
    }


    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }


    public String getSolution() {
        return solution;
    }


    public void setSolution(String solution) {
        this.solution = solution;
    }

}