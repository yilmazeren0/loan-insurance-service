package com.example.loanservice.strategy.factory;

import com.example.loanservice.strategy.impl.LoanApprovalStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LoanStrategyFactory {
    private final Map<String, LoanApprovalStrategy> strategies;

    public LoanStrategyFactory(List<LoanApprovalStrategy> strategyList) {
        strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        strategy -> strategy.getClass().getAnnotation(Component.class).value(),
                        Function.identity()
                ));
    }

    public LoanApprovalStrategy getStrategy(String loanType) {
        return strategies.get(loanType);
    }
}