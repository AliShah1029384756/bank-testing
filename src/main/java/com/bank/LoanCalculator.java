package com.bank;

public class LoanCalculator {
    public double calculateMonthlyPayment(double principal, double annualRate, int months) {
        if (principal <= 0) throw new IllegalArgumentException("Principal must be positive");
        if (annualRate < 0) throw new IllegalArgumentException("Rate cannot be negative");
        if (months <= 0) throw new IllegalArgumentException("Months must be positive");
        if (annualRate == 0) return principal / months;
        double monthlyRate = annualRate / 100 / 12;
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }

    public double calculateTotalPayment(double principal, double annualRate, int months) {
        return calculateMonthlyPayment(principal, annualRate, months) * months;
    }

    public double calculateInterest(double principal, double annualRate, int months) {
        return calculateTotalPayment(principal, annualRate, months) - principal;
    }
}