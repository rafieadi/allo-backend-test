package com.example.finance.controller;

import com.example.finance.service.FinanceDataStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finance/data")
public class FinanceController {

    private final FinanceDataStore dataStore;

    public FinanceController(FinanceDataStore store) {
        this.dataStore = store;
    }

    @GetMapping("/{resourceType}")
    public Object getData(@PathVariable("resourceType") String resourceType) {
        return dataStore.get(resourceType);
    }
}

