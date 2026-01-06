package com.example.finance.utill;

import org.springframework.stereotype.Component;

@Component
public class SpreadCalculator {

    private static final String GITHUB_USERNAME = "rafieadi";

    public double calculateUsdBuySpread(double rateUsd) {

        int asciiSum = GITHUB_USERNAME
                .toLowerCase()
                .chars()
                .sum();

        double spreadFactor = (asciiSum % 1000) / 100000.0;

        return (1 / rateUsd) * (1 + spreadFactor);
    }
}
