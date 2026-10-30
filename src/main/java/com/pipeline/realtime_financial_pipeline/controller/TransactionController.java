package com.pipeline.realtime_financial_pipeline.controller;

import com.pipeline.realtime_financial_pipeline.model.FinancialTransaction;
import com.pipeline.realtime_financial_pipeline.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<FinancialTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
