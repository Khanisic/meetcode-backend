package com.meetcode.backend_meetcode.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetcode.backend_meetcode.entity.Question;
import com.meetcode.backend_meetcode.repository.QuestionRepository;
import com.meetcode.backend_meetcode.service.QuestionService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Question getQuestionById(UUID id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Question getQuestionByTitleSlug(String titleSlug) {
        return questionRepository.findByTitleSlug(titleSlug)
                .orElseThrow(() -> new RuntimeException("Question not found with titleSlug: " + titleSlug));
    }

} 