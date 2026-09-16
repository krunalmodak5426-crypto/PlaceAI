package com.krunal.placeai.dto;

import java.util.Map;

public class AwtTestRequest {

    private Long userId;

    private Map<Long, String> answers;

    public AwtTestRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }
}