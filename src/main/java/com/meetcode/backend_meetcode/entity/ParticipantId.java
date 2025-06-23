package com.meetcode.backend_meetcode.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import lombok.Data;

@Data
public class ParticipantId implements Serializable {
    private UUID cid;
    private String username;

    public ParticipantId() {}

    public ParticipantId(UUID cid, String username) {
        this.cid = cid;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantId that = (ParticipantId) o;
        return Objects.equals(cid, that.cid) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, username);
    }
} 