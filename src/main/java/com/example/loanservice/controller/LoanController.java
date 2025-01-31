package com.example.loanservice.controller;

import com.example.loanservice.dto.LoanApplicationDTO;
import com.example.loanservice.entity.LoanApplication;
import com.example.loanservice.exception.ResourceNotFoundException;
import com.example.loanservice.mapper.LoanApplicationMapper;
import com.example.loanservice.repository.LoanApplicationRepository;
import com.example.loanservice.strategy.factory.LoanStrategyFactory;
import com.example.loanservice.strategy.impl.LoanApprovalStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanApplicationRepository loanRepository;
    @Autowired
    private final LoanApplicationMapper mapper;
    private final LoanStrategyFactory loanStrategyFactory;



    @PostMapping("/apply")
    public LoanApplication applyLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, @RequestParam String loanType) {
        LoanApplication loanApplication = mapper.toEntity(loanApplicationDTO);
        loanApplication.setStatus("PENDING");

        LoanApprovalStrategy strategy = loanStrategyFactory.getStrategy(loanType);

        if (strategy == null) {
            throw new IllegalArgumentException("This is invalid credit instance : " + loanType);
        }

        boolean isApproved = strategy.approveLoan(loanApplication);
        loanApplication.setStatus(isApproved ? "APPROVED" : "REJECTED");

        return loanRepository.save(loanApplication);
    }
    @GetMapping("/status/{id}")
    public LoanApplication checkStatus(@PathVariable long id) {
        return loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LoanApplication with id " + id));
    }
}
