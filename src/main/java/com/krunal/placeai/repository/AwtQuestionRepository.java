package com.krunal.placeai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krunal.placeai.entity.AwtQuestion;

@Repository
public interface AwtQuestionRepository extends JpaRepository<AwtQuestion, Long> {

    List<AwtQuestion> findByTopic(String topic);

    List<AwtQuestion> findByDifficulty(String difficulty);
}