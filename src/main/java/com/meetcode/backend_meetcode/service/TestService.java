package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.entity.Test;

public interface TestService {
    List<Test> getTestsByQuestionId(UUID qid);
    Test createTest(Test test);
    Test updateTest(Test test);
    void deleteTest(Long id);
} 