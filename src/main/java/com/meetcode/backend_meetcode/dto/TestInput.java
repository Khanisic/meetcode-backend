package com.meetcode.backend_meetcode.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class TestInput {
    private UUID qid;
    private String test;
    private String expectedOutput;
} 