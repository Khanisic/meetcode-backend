package com.meetcode.backend_meetcode.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetcode.backend_meetcode.entity.Example;
import com.meetcode.backend_meetcode.repository.ExampleRepository;
import com.meetcode.backend_meetcode.service.ExampleService;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public List<Example> getExamplesByQuestionId(UUID qid) {
        return exampleRepository.findByQid(qid);
    }

    @Override
    public Example createExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public Example updateExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public void deleteExample(Long id) {
        exampleRepository.deleteById(id);
    }
} 