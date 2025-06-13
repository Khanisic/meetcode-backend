package com.meetcode.backend_meetcode.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetcode.backend_meetcode.entity.Test;
import com.meetcode.backend_meetcode.repository.TestRepository;
import com.meetcode.backend_meetcode.service.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> getTestsByQuestionId(UUID qid) {
        return testRepository.findByQid(qid);
    }

    @Override
    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
} 