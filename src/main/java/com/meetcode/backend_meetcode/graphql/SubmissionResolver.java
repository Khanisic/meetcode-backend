package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.SubmissionInput;
import com.meetcode.backend_meetcode.entity.Question;
import com.meetcode.backend_meetcode.entity.Submission;
import com.meetcode.backend_meetcode.entity.User;
import com.meetcode.backend_meetcode.service.QuestionService;
import com.meetcode.backend_meetcode.service.SubmissionService;
import com.meetcode.backend_meetcode.service.UserService;

@Controller
public class SubmissionResolver {

    @Autowired
    private SubmissionService submissionService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private QuestionService questionService;

    @QueryMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @QueryMapping
    public Submission getSubmissionById(@Argument UUID id) {
        return submissionService.getSubmissionById(id);
    }

    @QueryMapping
    public List<Submission> getSubmissionsByUsername(@Argument String username) {
        return submissionService.getSubmissionsByUsername(username);
    }

    @QueryMapping
    public List<Submission> getSubmissionsByQuestionId(@Argument UUID questionId) {
        return submissionService.getSubmissionsByQuestionId(questionId);
    }

    @QueryMapping
    public List<Submission> getSubmissionsByUserAndQuestion(@Argument String username, @Argument String titleSlug) {
        return submissionService.getSubmissionsByUserAndQuestion(username, titleSlug);
    }

    @QueryMapping
    public List<Submission> getSubmissionsByMode(@Argument String submissionMode) {
        return submissionService.getSubmissionsByMode(submissionMode);
    }


    @QueryMapping
    public List<Submission> getUserSubmissionsOrderedByDate(@Argument String username) {
        return submissionService.getUserSubmissionsOrderedByDate(username);
    }

    @QueryMapping
    public List<Submission> getQuestionSubmissionsOrderedByScore(@Argument UUID questionId) {
        return submissionService.getQuestionSubmissionsOrderedByScore(questionId);
    }

    @MutationMapping
    public Submission createSubmission(@Argument SubmissionInput submissionInput) {
        // Validate input
        if (submissionInput.getUsername() == null || submissionInput.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username is required and cannot be empty");
        }
        if (submissionInput.getQuestionId() == null) {
            throw new RuntimeException("Question ID is required");
        }
        
        // Get user and question entities
        User user = userService.getUserByUsername(submissionInput.getUsername());
        Question question = questionService.getQuestionById(submissionInput.getQuestionId());
        
        if (user == null) {
            throw new RuntimeException("User not found with username: " + submissionInput.getUsername());
        }
        if (question == null) {
            throw new RuntimeException("Question not found with ID: " + submissionInput.getQuestionId());
        }
        
        Submission submission = new Submission();
        submission.setUsername(submissionInput.getUsername());
        submission.setQuestion(question);
        submission.setSubmissionScore(submissionInput.getSubmissionScore());
        submission.setSubmissionMode(submissionInput.getSubmissionMode());

        
        return submissionService.createSubmission(submission);
    }

    @MutationMapping
    public Submission updateSubmission(@Argument UUID id, @Argument SubmissionInput submissionInput) {
        Submission existingSubmission = submissionService.getSubmissionById(id);
        
        if (existingSubmission == null) {
            throw new RuntimeException("Submission not found with id: " + id);
        }
        
        // Update fields
        if (submissionInput.getUsername() != null) {
            User user = userService.getUserByUsername(submissionInput.getUsername());
            if (user != null) {
                existingSubmission.setUsername(submissionInput.getUsername());
            }
        }
        
        if (submissionInput.getUsername() != null) {
            existingSubmission.setUsername(submissionInput.getUsername());
        }
        
        if (submissionInput.getQuestionId() != null) {
            Question question = questionService.getQuestionById(submissionInput.getQuestionId());
            if (question != null) {
                existingSubmission.setQuestion(question);
            }
        }
        
        if (submissionInput.getSubmissionScore() != null) {
            existingSubmission.setSubmissionScore(submissionInput.getSubmissionScore());
        }
        
        if (submissionInput.getSubmissionMode() != null) {
            existingSubmission.setSubmissionMode(submissionInput.getSubmissionMode());
        }
        
        
        
        return submissionService.updateSubmission(existingSubmission);
    }

    @MutationMapping
    public Boolean deleteSubmission(@Argument UUID id) {
        try {
            submissionService.deleteSubmission(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 