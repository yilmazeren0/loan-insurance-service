package com.example.loanservice.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="loan_applications")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerId;
    private double amount;
    private String status;

}
