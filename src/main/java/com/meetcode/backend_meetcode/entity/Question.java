package com.meetcode.backend_meetcode.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "qid")
    private UUID qid;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "desc", columnDefinition = "text")
    private String desc;

    @Column(name = "tags")
    private List<String> tags;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "examples")
    private String examples;

    @Column(name = "tests")
    private String tests;

    @Column(name = "starting", columnDefinition = "text")
    private String starting;

    @Column(name = "ending", columnDefinition = "text")
    private String ending;

    @Column(name = "solutions")
    private String solutions;

    @Column(name = "code", columnDefinition = "text")
    private String code;

    @Column(name = "constraints")
    private List<String> constraints;

    @Column(name = "titleSlug")
    private String titleSlug;
} 