package com.example.loanservice.controller;

import com.example.loanservice.dto.LoanApplicationDTO;
import com.example.loanservice.entity.LoanApplication;
import com.example.loanservice.exception.ResourceNotFoundException;
import com.example.loanservice.mapper.LoanApplicationMapper;
import com.example.loanservice.repository.LoanApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanApplicationRepository loanRepository;
    @Autowired
    private final LoanApplicationMapper mapper;


    @PostMapping("/apply")
    public LoanApplication applyLoan(@RequestBody LoanApplicationDTO loanApplicationDTO) {
        LoanApplication loanApplication = mapper.toEntity(loanApplicationDTO);
        loanApplication.setStatus("PENDING");
        return loanRepository.save(loanApplication);
    }
    @GetMapping("/status/{id}")
    public LoanApplication checkStatus(@PathVariable long id) {
        return loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LoanApplication with id " + id));
    }
}
