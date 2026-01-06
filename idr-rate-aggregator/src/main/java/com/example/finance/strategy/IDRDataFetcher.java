package com.example.finance.strategy;

public interface IDRDataFetcher {
    String getResourceType();
    Object fetchData();
}
