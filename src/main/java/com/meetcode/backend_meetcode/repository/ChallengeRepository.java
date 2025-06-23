package com.meetcode.backend_meetcode.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {
    List<Challenge> findByStatus(String status);
    
    @Query("SELECT c FROM Challenge c WHERE c.status = 'OPEN'")
    List<Challenge> findOpenChallenges();
} 