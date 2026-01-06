package com.example.finance.strategy;


import com.example.finance.utill.SpreadCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LatestIdrRatesFetcherTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private LatestIdrRatesFetcher fetcher;

    @BeforeEach
    void setup() {
        SpreadCalculator spreadCalculator = new SpreadCalculator();
        fetcher = new LatestIdrRatesFetcher(webClient, spreadCalculator);
    }

    @Test
    void shouldAddUsdBuySpread() {

        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 0.000065);
        mockResponse.put("rates", rates);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString()))
                .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(Map.class)))
                .thenReturn(Mono.just(mockResponse));

        Object result = fetcher.fetchData();

        assertNotNull(result);

        Map<?, ?> resultMap = (Map<?, ?>) result;
        assertTrue(resultMap.containsKey("USD_BuySpread_IDR"));
    }
}

