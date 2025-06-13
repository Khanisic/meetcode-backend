package com.meetcode.backend_meetcode.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "solutions")
@IdClass(SolutionId.class)
@Data
public class Solution {
    @Id
    @Column(name = "qid")
    private UUID qid;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "java", columnDefinition = "text")
    private String java;

    @Column(name = "time")
    private String time;

    @Column(name = "space")
    private String space;

    @Column(name = "approach")
    private String approach;

    @ManyToOne
    @JoinColumn(name = "qid", insertable = false, updatable = false)
    private Question question;
} 