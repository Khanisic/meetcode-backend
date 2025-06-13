package com.meetcode.backend_meetcode.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.Solution;
import com.meetcode.backend_meetcode.entity.SolutionId;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, SolutionId> {
    List<Solution> findByQid(UUID qid);
} 