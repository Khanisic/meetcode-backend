package com.meetcode.backend_meetcode.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    List<Example> findByQid(UUID qid);
} 