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

import com.krunal.placeai.dto.CodingProblemRequest;
import com.krunal.placeai.entity.CodingProblem;
import com.krunal.placeai.service.CodingProblemService;

@RestController
@RequestMapping("/api/coding")
public class CodingProblemController {

    @Autowired
    private CodingProblemService service;


    // =========================================================
    // GET ALL CODING PROBLEMS
    // =========================================================

    @GetMapping("/problems")
    public List<CodingProblem> getAllProblems() {

        return service.getAllProblems();
    }


    // =========================================================
    // GET ONE PROBLEM BY ID
    // =========================================================

    @GetMapping("/problems/{id}")
    public CodingProblem getProblem(
            @PathVariable Long id) {

        return service.getProblem(id);
    }


    // =========================================================
    // GET PROBLEMS BY LANGUAGE
    // =========================================================

    @GetMapping("/problems/language/{language}")
    public List<CodingProblem> getByLanguage(
            @PathVariable String language) {

        return service.getByLanguage(language);
    }


    // =========================================================
    // GET PROBLEMS BY DIFFICULTY
    // =========================================================

    @GetMapping("/problems/difficulty/{difficulty}")
    public List<CodingProblem> getByDifficulty(
            @PathVariable String difficulty) {

        return service.getByDifficulty(difficulty);
    }


    // =========================================================
    // GET PROBLEMS BY COMPANY
    // =========================================================

    @GetMapping("/problems/company/{company}")
    public List<CodingProblem> getByCompany(
            @PathVariable String company) {

        return service.getByCompany(company);
    }


    // =========================================================
    // FILTER BY LANGUAGE + DIFFICULTY
    // =========================================================

    @GetMapping("/problems/filter")
    public List<CodingProblem> getByLanguageAndDifficulty(
            @RequestParam String language,
            @RequestParam String difficulty) {

        return service.getByLanguageAndDifficulty(
                language,
                difficulty
        );
    }


    // =========================================================
    // PREVIOUSLY ASKED
    // =========================================================

    @GetMapping("/problems/previously-asked")
    public List<CodingProblem> getPreviouslyAsked() {

        return service.getPreviouslyAsked();
    }


    // =========================================================
    // ADD ONE PROBLEM
    // =========================================================

    @PostMapping("/problems")
    public CodingProblem addProblem(
            @RequestBody CodingProblemRequest request) {

        return service.addProblem(request);
    }


    // =========================================================
    // ADD MULTIPLE PROBLEMS
    // =========================================================

    @PostMapping("/problems/bulk")
    public List<CodingProblem> addProblems(
            @RequestBody List<CodingProblemRequest> requests) {

        return requests.stream()
                .map(request -> service.addProblem(request))
                .toList();
    }


    // =========================================================
    // DELETE PROBLEM
    // =========================================================

    @DeleteMapping("/problems/{id}")
    public String deleteProblem(
            @PathVariable Long id) {

        service.deleteProblem(id);

        return "Coding problem deleted successfully";
    }
}