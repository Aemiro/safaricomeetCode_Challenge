package com.example.demo.repositories;

import com.example.demo.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    @Query(value = "select * from interview_results where candidate_name=:name", nativeQuery = true)
    Candidate getCandidateByName(@Param("name") String name);
}
