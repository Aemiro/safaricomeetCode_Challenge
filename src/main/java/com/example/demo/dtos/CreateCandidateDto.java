package com.example.demo.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CreateCandidateDto(
        @NotEmpty
        String candidateName,
        @NotEmpty
        @Max(100)
        @Min(0)
        double candidateScore,
        boolean isSuccessful) {
}
