package com.meetcode.backend_meetcode.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import lombok.Data;

@Data
public class SolutionId implements Serializable {
    private UUID qid;
    private Long id;

    public SolutionId() {}

    public SolutionId(UUID qid, Long id) {
        this.qid = qid;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionId that = (SolutionId) o;
        return Objects.equals(qid, that.qid) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qid, id);
    }
} 