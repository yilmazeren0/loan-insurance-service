package com.example.loanservice.strategy.impl;

import com.example.loanservice.entity.LoanApplication;
import org.springframework.stereotype.Component;

@Component("PERSONAL")
public class PersonalLoanStrategy implements LoanApprovalStrategy {
    @Override
    public boolean approveLoan(LoanApplication application) {
        return application.getAmount() <= 100_000;
    }
}