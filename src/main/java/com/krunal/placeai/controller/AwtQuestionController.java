package com.krunal.placeai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krunal.placeai.dto.AwtAnswerRequest;
import com.krunal.placeai.dto.AwtAnswerResult;
import com.krunal.placeai.dto.AwtQuestionResponse;
import com.krunal.placeai.entity.AwtQuestion;
import com.krunal.placeai.service.AwtQuestionService;

@RestController
@RequestMapping("/api/awt/questions")
public class AwtQuestionController {

    @Autowired
    private AwtQuestionService awtQuestionService;


    // ==========================================
    // GET ALL QUESTIONS
    // ==========================================

    @GetMapping
    public List<AwtQuestion> getAllQuestions() {

        return awtQuestionService.getAllQuestions();
    }


    // ==========================================
    // GET QUESTION BY ID
    // ==========================================

    @GetMapping("/{id}")
    public AwtQuestion getQuestionById(
            @PathVariable Long id) {

        return awtQuestionService.getQuestionById(id);
    }


    // ==========================================
    // CHECK ANSWER
    // ==========================================

    @PostMapping("/check")
    public AwtAnswerResult checkAnswer(
            @RequestBody AwtAnswerRequest request) {

        return awtQuestionService.checkAnswer(
                request.getQuestionId(),
                request.getAnswer()
        );
    }


    // ==========================================
    // ADD ONE QUESTION
    // ==========================================

    @PostMapping
    public AwtQuestion addQuestion(
            @RequestBody AwtQuestion question) {

        return awtQuestionService.addQuestion(question);
    }


    // ==========================================
    // ADD QUESTIONS IN BULK
    // ==========================================

    @PostMapping("/bulk")
    public List<AwtQuestion> addQuestions(
            @RequestBody List<AwtQuestion> questions) {

        return awtQuestionService.addQuestions(questions);
    }


    // ==========================================
    // GET QUESTIONS BY TOPIC
    // ==========================================

    @GetMapping("/topic")
    public List<AwtQuestion> getQuestionsByTopic(
            @RequestParam String topic) {

        return awtQuestionService.getQuestionsByTopic(topic);
    }


    // ==========================================
    // GET QUESTIONS BY DIFFICULTY
    // ==========================================

    @GetMapping("/difficulty")
    public List<AwtQuestion> getQuestionsByDifficulty(
            @RequestParam String difficulty) {

        return awtQuestionService.getQuestionsByDifficulty(difficulty);
    }


    // ==========================================
    // STUDENT QUESTIONS
    // Correct answers are hidden
    // ==========================================

    @GetMapping("/student")
    public List<AwtQuestionResponse> getStudentQuestions() {

        return awtQuestionService.getStudentQuestions();
    }


    // ==========================================
    // DELETE QUESTION
    // ==========================================

    @DeleteMapping("/{id}")
    public String deleteQuestion(
            @PathVariable Long id) {

        awtQuestionService.deleteQuestion(id);

        return "Question deleted successfully";
    }

}