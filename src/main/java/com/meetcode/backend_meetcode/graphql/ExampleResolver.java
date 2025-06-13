package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.ExampleInput;
import com.meetcode.backend_meetcode.entity.Example;
import com.meetcode.backend_meetcode.service.ExampleService;

@Controller
public class ExampleResolver {

    @Autowired
    private ExampleService exampleService;

    @QueryMapping
    public List<Example> getExamplesByQuestionId(@Argument UUID qid) {
        return exampleService.getExamplesByQuestionId(qid);
    }

    @MutationMapping
    public Example createExample(@Argument("example") ExampleInput exampleInput) {
        Example example = mapToExample(exampleInput);
        return exampleService.createExample(example);
    }

    @MutationMapping
    public Example updateExample(@Argument("example") ExampleInput exampleInput) {
        Example example = mapToExample(exampleInput);
        return exampleService.updateExample(example);
    }

    private Example mapToExample(ExampleInput input) {
        Example example = new Example();
        example.setQid(input.getQid());
        example.setTest(input.getTest());
        example.setExpectedOutput(input.getExpectedOutput());
        return example;
    }

    @MutationMapping
    public Boolean deleteExample(@Argument Long id) {
        try {
            exampleService.deleteExample(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 