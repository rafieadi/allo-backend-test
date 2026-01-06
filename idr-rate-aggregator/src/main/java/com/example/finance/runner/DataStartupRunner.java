package com.example.finance.runner;

import com.example.finance.service.FinanceDataStore;
import com.example.finance.strategy.IDRDataFetcher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataStartupRunner implements ApplicationRunner {

    private final List<IDRDataFetcher> fetchers;
    private final FinanceDataStore store;

    public DataStartupRunner(List<IDRDataFetcher> fetchers,
                             FinanceDataStore store) {
        this.fetchers = fetchers;
        this.store = store;
    }

    @Override
    public void run(ApplicationArguments args) {
        fetchers.forEach(fetcher ->
                store.put(fetcher.getResourceType(), fetcher.fetchData())
        );
    }
}

