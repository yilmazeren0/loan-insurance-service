package com.example.loanservice.strategy.impl;

import com.example.loanservice.entity.LoanApplication;

public interface LoanApprovalStrategy {
    boolean approveLoan(LoanApplication application);
}