package com.example.demo.controllers;

import com.example.demo.dtos.CreateCandidateDto;
import com.example.demo.models.Candidate;
import com.example.demo.services.CandidateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("candidates")
public class CandidateController {
    @Autowired
    CandidateService candidateService;
    @GetMapping("get-candidate/{id}")
    ResponseEntity<Candidate> getCandidate(@PathVariable("id")UUID candidateId){
        try{
            Candidate candidate=candidateService.getCandidate(candidateId);
            if(candidate==null){
                return new ResponseEntity("Record Not Found", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(candidate);
        }catch (EntityNotFoundException e){
            return new ResponseEntity("Record Not Found", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("get-candidate")
    ResponseEntity<Candidate> getCandidateByName(@RequestParam("name") String candidateName){
        Candidate candidate= candidateService.getCandidateByName(candidateName);
        if(candidate==null){
            return new ResponseEntity("Record Not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(candidate);
    }
    @DeleteMapping("delete-candidate/{id}")
    ResponseEntity deleteCandidate(@PathVariable("id") UUID id){
        Candidate candidate= candidateService.getCandidate(id);
        if(candidate==null){
            return new ResponseEntity("Record Not Found", HttpStatus.NOT_FOUND);
        }
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("update-candidate/{id}")
    ResponseEntity updateCandidate(@PathVariable("id") UUID id, @RequestBody() CreateCandidateDto updateCandidateDto){
        if(updateCandidateDto.candidateScore()>100 || updateCandidateDto.candidateScore()<=0){
            return new ResponseEntity("Candidate Score must be in between 100 & 0", HttpStatus.BAD_REQUEST);
        }
        Candidate c= candidateService.getCandidate(id);
        if(c==null){
            return new ResponseEntity("Record Not found", HttpStatus.NOT_FOUND);
        }
       
        return ResponseEntity.ok(candidateService.updateCandidate(id, updateCandidateDto));
    }
    @GetMapping("get-candidates")
    ResponseEntity<List<Candidate>> getCandidates(){
        return ResponseEntity.ok(candidateService.getCandidates());
    }
    @PostMapping("create-candidate")
    ResponseEntity<Candidate> createCandidate(@RequestBody() CreateCandidateDto createCandidateDto){
        if(createCandidateDto.candidateScore()>100 || createCandidateDto.candidateScore()<=0){
            return new ResponseEntity("Candidate Score must be in between 100 & 0", HttpStatus.BAD_REQUEST);
        }
        Candidate existingCandidate= candidateService.getCandidateByName(createCandidateDto.candidateName());
        if(existingCandidate!=null) {
            return new ResponseEntity("Record already exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(candidateService.createCandidate(createCandidateDto));
    }
}
