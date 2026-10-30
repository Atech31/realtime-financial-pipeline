package com.pipeline.realtime_financial_pipeline.service;

import com.pipeline.realtime_financial_pipeline.model.FinancialTransaction;
import com.pipeline.realtime_financial_pipeline.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class TransactionStreamService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final List<String> symbols = List.of("AAPL", "GOOGL", "TSLA", "BTC", "ETH", "AMZN");
    private final List<String> types = List.of("BUY", "SELL");
    private final Random random = new Random();

    @Scheduled(fixedRate = 3000) // Generates a live transaction every 3 seconds
    public void generateAndStreamTransaction() {
        FinancialTransaction tx = new FinancialTransaction();
        tx.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        tx.setSymbol(symbols.get(random.nextInt(symbols.size())));
        tx.setTransactionType(types.get(random.nextInt(types.size())));
        tx.setAmount(Math.round((random.nextDouble() * 5000 + 100) * 100.0) / 100.0);
        tx.setPrice(Math.round((random.nextDouble() * 1000 + 50) * 100.0) / 100.0);

        // Save to PostgreSQL
        transactionRepository.save(tx);

        // Broadcast to WebSocket clients listening on /topic/transactions
        messagingTemplate.convertAndSend("/topic/transactions", tx);
        System.out.println("Broadcasted transaction: " + tx.getTransactionId() + " | " + tx.getSymbol());
    }
}
