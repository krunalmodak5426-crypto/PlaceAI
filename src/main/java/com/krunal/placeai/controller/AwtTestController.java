package com.krunal.placeai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krunal.placeai.dto.AwtQuestionResponse;
import com.krunal.placeai.dto.AwtResultResponse;
import com.krunal.placeai.dto.AwtSubmitRequest;
import com.krunal.placeai.service.AwtTestService;

@RestController
@RequestMapping("/api/awt/test")
public class AwtTestController {

    @Autowired
    private AwtTestService awtTestService;

    @GetMapping("/start")
    public List<AwtQuestionResponse> startTest() {
        return awtTestService.generateTest();
    }

    @PostMapping("/submit")
    public AwtResultResponse submitTest(
            @RequestBody AwtSubmitRequest request) {

        return awtTestService.submitTest(
                request.getAnswers()
        );
    }
}