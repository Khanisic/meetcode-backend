package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.SolutionInput;
import com.meetcode.backend_meetcode.entity.Solution;
import com.meetcode.backend_meetcode.service.SolutionService;

@Controller
public class SolutionResolver {

    @Autowired
    private SolutionService solutionService;

    @QueryMapping
    public List<Solution> getSolutionsByQuestionId(@Argument UUID qid) {
        return solutionService.getSolutionsByQuestionId(qid);
    }

    @MutationMapping
    public Solution createSolution(@Argument("solution") SolutionInput solutionInput) {
        Solution solution = mapToSolution(solutionInput);
        return solutionService.createSolution(solution);
    }

    @MutationMapping
    public Solution updateSolution(@Argument("solution") SolutionInput solutionInput) {
        Solution solution = mapToSolution(solutionInput);
        return solutionService.updateSolution(solution);
    }

    private Solution mapToSolution(SolutionInput input) {
        Solution solution = new Solution();
        solution.setQid(input.getQid());
        solution.setId(input.getId());
        solution.setJava(input.getJava());
        solution.setTime(input.getTime());
        solution.setSpace(input.getSpace());
        solution.setApproach(input.getApproach());
        return solution;
    }

    @MutationMapping
    public Boolean deleteSolution(@Argument UUID qid, @Argument Long id) {
        try {
            solutionService.deleteSolution(qid, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 