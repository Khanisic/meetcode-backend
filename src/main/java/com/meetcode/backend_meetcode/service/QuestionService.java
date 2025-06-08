package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.entity.Question;

public interface QuestionService {
    List<Question> getAllQuestions();
    Question getQuestionById(UUID id);
    Question getQuestionByTitleSlug(String titleSlug);
} 