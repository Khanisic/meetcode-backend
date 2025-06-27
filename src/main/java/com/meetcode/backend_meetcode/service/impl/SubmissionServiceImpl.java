package com.meetcode.backend_meetcode.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetcode.backend_meetcode.entity.Submission;
import com.meetcode.backend_meetcode.repository.SubmissionRepository;
import com.meetcode.backend_meetcode.service.SubmissionService;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public Submission updateSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public void deleteSubmission(UUID submissionId) {
        submissionRepository.deleteById(submissionId);
    }

    @Override
    public Submission getSubmissionById(UUID submissionId) {
        return submissionRepository.findById(submissionId).orElse(null);
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getSubmissionsByUsername(String username) {
        return submissionRepository.findByUsername(username);
    }

    @Override
    public List<Submission> getSubmissionsByQuestionId(UUID questionId) {
        return submissionRepository.findByQuestionQid(questionId);
    }

    @Override
    public List<Submission> getSubmissionsByUserAndQuestion(String username, String titleSlug) {
        return submissionRepository.findByUsernameAndQuestionTitleSlug(username, titleSlug);
    }

    @Override
    public List<Submission> getSubmissionsByMode(String submissionMode) {
        return submissionRepository.findBySubmissionMode(submissionMode);
    }

    @Override
    public List<Submission> getUserSubmissionsOrderedByDate(String username) {
        return submissionRepository.findByUsernameOrderByDateSubmittedDesc(username);
    }

    @Override
    public List<Submission> getQuestionSubmissionsOrderedByScore(UUID questionId) {
        return submissionRepository.findByQuestionIdOrderByScoreDesc(questionId);
    }
} 