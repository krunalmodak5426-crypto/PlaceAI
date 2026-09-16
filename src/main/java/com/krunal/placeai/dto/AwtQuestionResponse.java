package com.krunal.placeai.dto;

public class AwtQuestionResponse {

    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String topic;
    private String difficulty;

    public AwtQuestionResponse() {
    }

    public AwtQuestionResponse(
            Long id,
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            String topic,
            String difficulty) {

        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getTopic() {
        return topic;
    }

    public String getDifficulty() {
        return difficulty;
    }
}