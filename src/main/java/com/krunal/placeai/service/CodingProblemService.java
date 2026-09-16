package com.krunal.placeai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krunal.placeai.dto.CodingProblemRequest;
import com.krunal.placeai.entity.CodingProblem;
import com.krunal.placeai.repository.CodingProblemRepository;

@Service
public class CodingProblemService {

    @Autowired
    private CodingProblemRepository repository;


    // =====================================================
    // ADD ONE CODING PROBLEM
    // =====================================================

    public CodingProblem addProblem(
            CodingProblemRequest request) {

        CodingProblem problem =
                new CodingProblem();


        problem.setTitle(
                request.getTitle()
        );


        problem.setLanguage(
                request.getLanguage()
        );


        problem.setDifficulty(
                request.getDifficulty()
        );


        problem.setCompany(
                request.getCompany()
        );


        problem.setPreviouslyAsked(
                request.isPreviouslyAsked()
        );


        problem.setProblemStatement(
                request.getProblemStatement()
        );


        problem.setInputFormat(
                request.getInputFormat()
        );


        problem.setOutputFormat(
                request.getOutputFormat()
        );


        problem.setSampleInput(
                request.getSampleInput()
        );


        problem.setSampleOutput(
                request.getSampleOutput()
        );


        problem.setStarterCode(
                request.getStarterCode()
        );


        problem.setExpectedOutput(
                request.getExpectedOutput()
        );


        problem.setSolution(
                request.getSolution()
        );


        return repository.save(problem);
    }


    // =====================================================
    // GET ALL CODING PROBLEMS
    // =====================================================

    public List<CodingProblem> getAllProblems() {

        return repository.findAll();
    }


    // =====================================================
    // GET ONE PROBLEM BY ID
    // =====================================================

    public CodingProblem getProblem(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Coding problem not found: " + id
                        )
                );
    }


    // =====================================================
    // GET BY LANGUAGE
    // =====================================================

    public List<CodingProblem> getByLanguage(
            String language) {

        return repository.findByLanguageIgnoreCase(
                language
        );
    }


    // =====================================================
    // GET BY DIFFICULTY
    // =====================================================

    public List<CodingProblem> getByDifficulty(
            String difficulty) {

        return repository.findByDifficultyIgnoreCase(
                difficulty
        );
    }


    // =====================================================
    // GET BY COMPANY
    // =====================================================

    public List<CodingProblem> getByCompany(
            String company) {

        return repository.findByCompanyIgnoreCase(
                company
        );
    }


    // =====================================================
    // LANGUAGE + DIFFICULTY
    // =====================================================

    public List<CodingProblem>
    getByLanguageAndDifficulty(
            String language,
            String difficulty) {

        return repository
                .findByLanguageIgnoreCaseAndDifficultyIgnoreCase(
                        language,
                        difficulty
                );
    }


    // =====================================================
    // PREVIOUSLY ASKED
    // =====================================================

    public List<CodingProblem>
    getPreviouslyAsked() {

        return repository.findByPreviouslyAskedTrue();
    }


    // =====================================================
    // DELETE
    // =====================================================

    public void deleteProblem(Long id) {

        if (!repository.existsById(id)) {

            throw new RuntimeException(
                    "Coding problem not found: " + id
            );
        }

        repository.deleteById(id);
    }

}