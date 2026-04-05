package com.bank;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String transactionId, String type, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (!type.equals("DEPOSIT") && !type.equals("WITHDRAWAL") && !type.equals("TRANSFER"))
            throw new IllegalArgumentException("Invalid transaction type");
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}