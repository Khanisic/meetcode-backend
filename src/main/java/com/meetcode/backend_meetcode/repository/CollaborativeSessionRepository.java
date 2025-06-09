package com.meetcode.backend_meetcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.CollaborativeSession;

@Repository
public interface CollaborativeSessionRepository extends JpaRepository<CollaborativeSession, String> {
} 