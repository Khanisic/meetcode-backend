package com.meetcode.backend_meetcode.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "examples")
@Data
public class Example {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "qid", nullable = false)
    private UUID qid;

    @Column(name = "test", columnDefinition = "text")
    private String test;

    @Column(name = "expected_output", columnDefinition = "text")
    private String expectedOutput;

    @ManyToOne
    @JoinColumn(name = "qid", insertable = false, updatable = false)
    private Question question;
} 