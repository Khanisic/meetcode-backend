package com.meetcode.backend_meetcode.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    
    List<Submission> findByUsername(String username);
    
    List<Submission> findByQuestionQid(UUID questionId);
    
    List<Submission> findByUsernameAndQuestionTitleSlug(String username, String titleSlug);
    
    List<Submission> findBySubmissionMode(String submissionMode);
    
    
    @Query("SELECT s FROM Submission s WHERE s.username = :username ORDER BY s.dateSubmitted DESC")
    List<Submission> findByUsernameOrderByDateSubmittedDesc(@Param("username") String username);
    
    @Query("SELECT s FROM Submission s WHERE s.question.qid = :questionId ORDER BY s.submissionScore DESC")
    List<Submission> findByQuestionIdOrderByScoreDesc(@Param("questionId") UUID questionId);
    
    @Query("SELECT s FROM Submission s WHERE s.username = :username AND s.question.qid = :questionId ORDER BY s.dateSubmitted DESC")
    List<Submission> findUserSubmissionsForQuestion(@Param("username") String username, @Param("questionId") UUID questionId);
} 