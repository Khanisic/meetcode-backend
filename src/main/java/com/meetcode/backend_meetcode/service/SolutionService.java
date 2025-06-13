package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.entity.Solution;

public interface SolutionService {
    List<Solution> getSolutionsByQuestionId(UUID qid);
    Solution createSolution(Solution solution);
    Solution updateSolution(Solution solution);
    void deleteSolution(UUID qid, Long id);
} 