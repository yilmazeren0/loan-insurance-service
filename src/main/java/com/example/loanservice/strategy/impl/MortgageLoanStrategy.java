package com.example.loanservice.strategy.impl;

import com.example.loanservice.entity.LoanApplication;
import org.springframework.stereotype.Component;

@Component("MORTGAGE")
public class MortgageLoanStrategy implements LoanApprovalStrategy {
    @Override
    public boolean approveLoan(LoanApplication application) {
        return application.getAmount() <= 500_000;
    }
}