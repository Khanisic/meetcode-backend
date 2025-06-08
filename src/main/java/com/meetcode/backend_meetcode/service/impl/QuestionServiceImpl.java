package com.meetcode.backend_meetcode.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetcode.backend_meetcode.entity.Question;
import com.meetcode.backend_meetcode.repository.QuestionRepository;
import com.meetcode.backend_meetcode.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }

    @Override
    public Question getQuestionByTitleSlug(String titleSlug) {
        return questionRepository.findByTitleSlug(titleSlug)
                .orElseThrow(() -> new RuntimeException("Question not found with titleSlug: " + titleSlug));
    }

} 