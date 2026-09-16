package com.krunal.placeai.dto;

import java.util.List;

public class AwtSubmitRequest {

    private List<AwtAnswerRequest> answers;

    public AwtSubmitRequest() {
    }

    public AwtSubmitRequest(List<AwtAnswerRequest> answers) {
        this.answers = answers;
    }

    public List<AwtAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AwtAnswerRequest> answers) {
        this.answers = answers;
    }
}