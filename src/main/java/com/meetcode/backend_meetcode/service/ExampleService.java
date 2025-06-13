package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.entity.Example;

public interface ExampleService {
    List<Example> getExamplesByQuestionId(UUID qid);
    Example createExample(Example example);
    Example updateExample(Example example);
    void deleteExample(Long id);
} 