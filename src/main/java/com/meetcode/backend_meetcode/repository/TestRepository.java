package com.meetcode.backend_meetcode.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByQid(UUID qid);
} 