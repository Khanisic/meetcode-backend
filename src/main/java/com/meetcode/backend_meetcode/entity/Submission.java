package com.meetcode.backend_meetcode.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "qid", nullable = false)
    private Question question;

    @Column(name = "submission_score", nullable = false)
    private Integer submissionScore;

    @Column(name = "submission_mode", nullable = false)
    private String submissionMode;

    @Column(name = "date_submitted", nullable = false)
    private LocalDateTime dateSubmitted;


    @PrePersist
    protected void onCreate() {
        if (dateSubmitted == null) {
            dateSubmitted = LocalDateTime.now();
        }
    }
} 