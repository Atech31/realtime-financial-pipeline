package com.pipeline.realtime_financial_pipeline.repository;

import com.pipeline.realtime_financial_pipeline.model.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<FinancialTransaction, Long> {
}
