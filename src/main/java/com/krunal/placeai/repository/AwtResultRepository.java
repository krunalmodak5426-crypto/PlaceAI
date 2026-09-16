package com.krunal.placeai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krunal.placeai.entity.AwtResult;
import com.krunal.placeai.entity.User;

@Repository
public interface AwtResultRepository extends JpaRepository<AwtResult, Long> {

    List<AwtResult> findByStudent(User student);
}