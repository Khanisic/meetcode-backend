package com.meetcode.backend_meetcode.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborative_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollaborativeSession {
    @Id
    @Column(name = "session_id")
    private String sessionId;
    
    @Column(name = "document", columnDefinition = "text")
    private String document;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "session_active_users", joinColumns = @JoinColumn(name = "session_id"))
    @Column(name = "username")
    private Set<String> activeUsers = new HashSet<>();
} 
