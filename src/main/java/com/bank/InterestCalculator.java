package com.bank;

public class InterestCalculator {
    public double calculateSimpleInterest(double principal, double rate, double time) {
        if (principal <= 0 || rate < 0 || time <= 0)
            throw new IllegalArgumentException("Invalid input values");
        return (principal * rate * time) / 100;
    }

    public double calculateCompoundInterest(double principal, double rate, int times, double years) {
        if (principal <= 0 || rate < 0 || times <= 0 || years <= 0)
            throw new IllegalArgumentException("Invalid input values");
        return principal * Math.pow(1 + rate / (100 * times), times * years) - principal;
    }

    public double calculateFinalAmount(double principal, double rate, double time) {
        return principal + calculateSimpleInterest(principal, rate, time);
    }
}