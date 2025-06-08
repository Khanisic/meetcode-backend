package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.entity.Question;
import com.meetcode.backend_meetcode.service.QuestionService;

@Controller
public class QuestionResolver {

    @Autowired
    private QuestionService questionService;

    @QueryMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @QueryMapping
    public Question getQuestionById(@Argument UUID id) {
        return questionService.getQuestionById(id);
    }

    @QueryMapping
    public Question getQuestionByTitleSlug(@Argument String titleSlug) {
        return questionService.getQuestionByTitleSlug(titleSlug);
    }
} 