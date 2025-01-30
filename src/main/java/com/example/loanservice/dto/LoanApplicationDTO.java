package com.example.loanservice.dto;

import lombok.Data;

@Data
public class LoanApplicationDTO {
    private String customerId;
    private double amount;
}
