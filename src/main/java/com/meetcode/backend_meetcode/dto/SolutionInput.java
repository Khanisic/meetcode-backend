package com.meetcode.backend_meetcode.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class SolutionInput {
    private UUID qid;
    private Long id;
    private String java;
    private String time;
    private String space;
    private String approach;
} 