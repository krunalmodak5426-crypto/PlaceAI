package com.krunal.placeai.repository;

import com.krunal.placeai.entity.AiInterviewAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiInterviewAttemptRepository
        extends JpaRepository<AiInterviewAttempt, Long> {

    List<AiInterviewAttempt> findByUserIdOrderByAttemptedAtDesc(
            Long userId
    );

    List<AiInterviewAttempt> findByUserIdAndInterviewIdOrderByQuestionNumberAsc(
            Long userId,
            String interviewId
    );

    long countByUserId(Long userId);

    long countByUserIdAndResult(
            Long userId,
            String result
    );
}