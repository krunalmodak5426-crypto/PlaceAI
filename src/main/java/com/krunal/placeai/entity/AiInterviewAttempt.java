package com.krunal.placeai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_interview_attempts")
public class AiInterviewAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private String interviewId;


    @Column(nullable = false)
    private String language;


    @Column(nullable = false)
    private String difficulty;


    @Column(nullable = false)
    private String topic;


    @Column(nullable = false)
    private Integer questionNumber;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;


    @Column(columnDefinition = "TEXT")
    private String userAnswer;


    /*
     * CORRECT / WRONG / PARTIAL / NOT_EVALUATED
     */
    @Column(nullable = false)
    private String result;


    /*
     * Score from 0 to 10
     */
    @Column
    private Integer score;


    @Column(columnDefinition = "TEXT")
    private String feedback;


    @Column(nullable = false)
    private LocalDateTime attemptedAt;


    /* =====================================================
       CONSTRUCTOR
       ===================================================== */

    public AiInterviewAttempt() {
    }


    public AiInterviewAttempt(
            User user,
            String interviewId,
            String language,
            String difficulty,
            String topic,
            Integer questionNumber,
            String question,
            String userAnswer,
            String result,
            Integer score,
            String feedback
    ) {

        this.user = user;
        this.interviewId = interviewId;
        this.language = language;
        this.difficulty = difficulty;
        this.topic = topic;
        this.questionNumber = questionNumber;
        this.question = question;
        this.userAnswer = userAnswer;
        this.result = result;
        this.score = score;
        this.feedback = feedback;
        this.attemptedAt = LocalDateTime.now();
    }


    /* =====================================================
       AUTO DATE
       ===================================================== */

    @PrePersist
    protected void onCreate() {

        if (this.attemptedAt == null) {
            this.attemptedAt = LocalDateTime.now();
        }

    }


    /* =====================================================
       GETTERS
       ===================================================== */

    public Long getId() {
        return id;
    }


    public User getUser() {
        return user;
    }


    public String getInterviewId() {
        return interviewId;
    }


    public String getLanguage() {
        return language;
    }


    public String getDifficulty() {
        return difficulty;
    }


    public String getTopic() {
        return topic;
    }


    public Integer getQuestionNumber() {
        return questionNumber;
    }


    public String getQuestion() {
        return question;
    }


    public String getUserAnswer() {
        return userAnswer;
    }


    public String getResult() {
        return result;
    }


    public Integer getScore() {
        return score;
    }


    public String getFeedback() {
        return feedback;
    }


    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }


    /* =====================================================
       SETTERS
       ===================================================== */

    public void setId(Long id) {
        this.id = id;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }


    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }


    public void setQuestion(String question) {
        this.question = question;
    }


    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }


    public void setResult(String result) {
        this.result = result;
    }


    public void setScore(Integer score) {
        this.score = score;
    }


    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }

}