package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.entity.Submission;

public interface SubmissionService {
    
    Submission createSubmission(Submission submission);
    
    Submission updateSubmission(Submission submission);
    
    void deleteSubmission(UUID submissionId);
    
    Submission getSubmissionById(UUID submissionId);
    
    List<Submission> getAllSubmissions();
    
    List<Submission> getSubmissionsByUsername(String username);
    
    List<Submission> getSubmissionsByQuestionId(UUID questionId);
    
    List<Submission> getSubmissionsByUserAndQuestion(String username, String titleSlug);
    
    List<Submission> getSubmissionsByMode(String submissionMode);
    
    List<Submission> getUserSubmissionsOrderedByDate(String username);
    
    List<Submission> getQuestionSubmissionsOrderedByScore(UUID questionId);
} 