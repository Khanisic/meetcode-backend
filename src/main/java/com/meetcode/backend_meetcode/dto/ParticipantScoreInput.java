package com.meetcode.backend_meetcode.dto;

import lombok.Data;

@Data
public class ParticipantScoreInput {
    private String username;
    private Integer score;
    private Integer rank;
} 