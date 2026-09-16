package com.krunal.placeai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krunal.placeai.entity.CodingProblem;

public interface CodingProblemRepository
        extends JpaRepository<CodingProblem, Long> {

    List<CodingProblem> findByLanguageIgnoreCase(String language);

    List<CodingProblem> findByDifficultyIgnoreCase(String difficulty);

    List<CodingProblem> findByCompanyIgnoreCase(String company);

    List<CodingProblem> findByLanguageIgnoreCaseAndDifficultyIgnoreCase(
            String language,
            String difficulty
    );

    List<CodingProblem> findByPreviouslyAskedTrue();
}