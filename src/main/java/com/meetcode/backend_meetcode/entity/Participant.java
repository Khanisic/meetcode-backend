package com.meetcode.backend_meetcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.UUID;

@Entity
@Table(name = "participants")
@IdClass(ParticipantId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"challenge", "user"})
public class Participant {
    @Id
    @Column(name = "cid")
    private UUID cid;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "time")
    private String time;

    @Column(name = "score")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "cid", insertable = false, updatable = false)
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private User user;
}