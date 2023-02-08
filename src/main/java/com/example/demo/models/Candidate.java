package com.example.demo.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="interview_results")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "candidate_name", unique = true, length = 50)
    private String candidateName;
    @Column(name="candidate_score")
    private double candidateScore;
    @Column(name = "is_successful")
    private boolean isSuccessful=false;
    @Column(nullable = true, updatable = false)
    private Date createdAt;
    @Column(nullable = false, updatable = true)
    private Date updatedAt;
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @PrePersist
    public void onPrePersist() {
        setCreatedAt((new Date()));
        setUpdatedAt((new Date()));
    }

    @PreUpdate
    public void onPreUpdate() {
        setCreatedAt((new Date()));
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public double getCandidateScore() {
        return candidateScore;
    }

    public void setCandidateScore(double candidateScore) {
        this.candidateScore = candidateScore;
    }
    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
