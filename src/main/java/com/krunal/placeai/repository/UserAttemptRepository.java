package com.krunal.placeai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krunal.placeai.entity.UserAttempt;

public interface UserAttemptRepository extends JpaRepository<UserAttempt, Long> {

    List<UserAttempt> findByUserIdOrderByAttemptedAtDesc(Long userId);

    long countByUserIdAndType(Long userId, String type);

    long countByUserIdAndTypeAndStatus(
            Long userId,
            String type,
            String status
    );
}