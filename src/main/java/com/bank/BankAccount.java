package com.bank;

public class BankAccount {
    private String accountId;
    private String ownerName;
    private double balance;
    private boolean isActive;

    public BankAccount(String accountId, String ownerName, double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.isActive = true;
    }

    public void deposit(double amount) {
        if (!isActive) throw new IllegalStateException("Account is inactive");
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (!isActive) throw new IllegalStateException("Account is inactive");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        this.balance -= amount;
    }

    public void deactivate() { this.isActive = false; }
    public double getBalance() { return balance; }
    public String getAccountId() { return accountId; }
    public String getOwnerName() { return ownerName; }
    public boolean isActive() { return isActive; }
}