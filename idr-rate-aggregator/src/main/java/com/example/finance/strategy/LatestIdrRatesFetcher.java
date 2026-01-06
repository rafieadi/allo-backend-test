package com.example.finance.strategy;

import com.example.finance.utill.SpreadCalculator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class LatestIdrRatesFetcher implements IDRDataFetcher {

    private final WebClient webClient;
    private final SpreadCalculator spreadCalculator;

    public LatestIdrRatesFetcher(WebClient webClient,
                                 SpreadCalculator spreadCalculator) {
        this.webClient = webClient;
        this.spreadCalculator = spreadCalculator;
    }

    @Override
    public String getResourceType() {
        return "latest_idr_rates";
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object fetchData() {

        Map<String, Object> response = webClient.get()
                .uri("/latest?base=IDR")
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Double> rates =
                (Map<String, Double>) response.get("rates");

        Double usdRate = rates.get("USD");

        double spreadValue = spreadCalculator.calculateUsdBuySpread(usdRate);

        response.put("USD_BuySpread_IDR", spreadValue);

        return response;
    }
}


