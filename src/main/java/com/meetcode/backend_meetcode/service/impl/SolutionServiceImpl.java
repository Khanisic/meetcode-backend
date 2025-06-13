package com.meetcode.backend_meetcode.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetcode.backend_meetcode.entity.Solution;
import com.meetcode.backend_meetcode.entity.SolutionId;
import com.meetcode.backend_meetcode.repository.SolutionRepository;
import com.meetcode.backend_meetcode.service.SolutionService;

@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public List<Solution> getSolutionsByQuestionId(UUID qid) {
        return solutionRepository.findByQid(qid);
    }

    @Override
    public Solution createSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    @Override
    public Solution updateSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    @Override
    public void deleteSolution(UUID qid, Long id) {
        SolutionId solutionId = new SolutionId(qid, id);
        solutionRepository.deleteById(solutionId);
    }
} 