package com.example.loanservice.mapper;

import com.example.loanservice.dto.LoanApplicationDTO;
import com.example.loanservice.entity.LoanApplication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanApplicationMapper {
    LoanApplication toEntity(LoanApplicationDTO dto);
}