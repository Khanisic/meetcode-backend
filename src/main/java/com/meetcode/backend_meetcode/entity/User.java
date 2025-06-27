package com.meetcode.backend_meetcode.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_since")
    private LocalDateTime userSince;

    @Column(name = "score")
    private Integer score;
    
    @Column(name = "img_url")
    private String imgUrl;
    
    @Column(name = "is_verified")
    private Boolean isVerified = false;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
    
    @Column(name = "is_banned")
    private Boolean isBanned = false;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "is_admin")
    private Boolean isAdmin = false;
    
    @Column(name = "is_premium")
    private Boolean isPremium = false;
    
    @Column(name = "is_trial")
    private Boolean isTrial = false;
    
    @Column(name = "is_trial_expired")
    private Boolean isTrialExpired = false;
    
    @Column(name = "is_trial_active")
    private Boolean isTrialActive = false;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "banner")
    private String banner;

    @OneToMany(mappedBy = "username")
    private List<Submission> submissions;
    
    @PrePersist
    protected void onCreate() {
        userSince = LocalDateTime.now();
        score = 0;
        banner = "none";
    }
} 