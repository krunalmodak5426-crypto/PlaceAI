package com.krunal.placeai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krunal.placeai.dto.CodingSubmissionRequest;
import com.krunal.placeai.dto.CodingSubmissionResponse;
import com.krunal.placeai.service.CodingExecutionService;

@RestController
@RequestMapping("/api/coding")
public class CodingSubmissionController {

    @Autowired
    private CodingExecutionService codingExecutionService;

    @PostMapping("/submit")
    public ResponseEntity<CodingSubmissionResponse> submit(
            @RequestBody CodingSubmissionRequest request) {

        try {

            CodingSubmissionResponse result =
                    codingExecutionService.submit(request);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            e.printStackTrace();

            CodingSubmissionResponse error =
                    new CodingSubmissionResponse(
                            false,
                            "❌ Backend execution error.",
                            "",
                            "",
                            e.getMessage()
                    );

            return ResponseEntity
                    .status(500)
                    .body(error);
        }
    }
}