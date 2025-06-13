package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.TestInput;
import com.meetcode.backend_meetcode.entity.Test;
import com.meetcode.backend_meetcode.service.TestService;

@Controller
public class TestResolver {

    @Autowired
    private TestService testService;

    @QueryMapping
    public List<Test> getTestsByQuestionId(@Argument UUID qid) {
        return testService.getTestsByQuestionId(qid);
    }

    @MutationMapping
    public Test createTest(@Argument("test") TestInput testInput) {
        Test test = mapToTest(testInput);
        return testService.createTest(test);
    }

    @MutationMapping
    public Test updateTest(@Argument("test") TestInput testInput) {
        Test test = mapToTest(testInput);
        return testService.updateTest(test);
    }

    private Test mapToTest(TestInput input) {
        Test test = new Test();
        test.setQid(input.getQid());
        test.setTest(input.getTest());
        test.setExpectedOutput(input.getExpectedOutput());
        return test;
    }

    @MutationMapping
    public Boolean deleteTest(@Argument Long id) {
        try {
            testService.deleteTest(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 