package com.krunal.placeai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krunal.placeai.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}