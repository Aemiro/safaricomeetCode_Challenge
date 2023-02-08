package com.example.demo.services;

import com.example.demo.dtos.CreateCandidateDto;
import com.example.demo.models.Candidate;
import com.example.demo.repositories.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateService {
    @Autowired
    private  CandidateRepository candidateRepository;
//    public CandidateService(CandidateRepository candidateRepository){
//        this.candidateRepository=candidateRepository;
//    }
    public Candidate getCandidateByName(String name){

            Candidate candidate = candidateRepository.getCandidateByName(name);
            return candidate;

    }
    public Candidate getCandidate(UUID id){
            Optional<Candidate> c = candidateRepository.findById(id);
            return c.get();
    }
    public List<Candidate> getCandidates(){
        return candidateRepository.findAll();
    }
    public void deleteCandidate(UUID id){
         candidateRepository.deleteById(id);
    }
    public Candidate createCandidate(CreateCandidateDto createCandidateDto){
        Candidate c=new Candidate();
        c.setCandidateName(createCandidateDto.candidateName());
        c.setCandidateScore(createCandidateDto.candidateScore());
        c.setSuccessful(createCandidateDto.isSuccessful());
        return candidateRepository.save(c);
    }
    public Candidate updateCandidate(UUID id, CreateCandidateDto createCandidateDto){
        Candidate candidate=this.getCandidate(id);
        if(candidate==null){
            throw new EntityNotFoundException("Record Not Found");
        }
        candidate.setCandidateName(createCandidateDto.candidateName());
        candidate.setCandidateScore(createCandidateDto.candidateScore());
        candidate.setSuccessful(createCandidateDto.isSuccessful());
        return candidateRepository.save(candidate);
    }
}
