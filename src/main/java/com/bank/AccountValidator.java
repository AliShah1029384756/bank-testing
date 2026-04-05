package com.bank;

public class AccountValidator {
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return email.contains("@") && email.contains(".");
    }

    public boolean isValidAccountId(String accountId) {
        if (accountId == null) return false;
        return accountId.matches("ACC\\d{4}");
    }

    public boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 1000000;
    }

    public boolean isValidAge(int age) {
        return age >= 18 && age <= 100;
    }
}