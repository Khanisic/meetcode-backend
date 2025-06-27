package com.meetcode.backend_meetcode.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class SubmissionInput {
    private String username;
    private UUID questionId;
    private Integer submissionScore;
    private String submissionMode;
} 