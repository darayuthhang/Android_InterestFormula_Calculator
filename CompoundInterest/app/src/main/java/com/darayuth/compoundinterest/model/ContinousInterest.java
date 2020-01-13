package com.darayuth.compoundinterest.model;

public class ContinousInterest implements Interest{

    private double principalAmount;
    private double interestRate;
    private double Time;

    public ContinousInterest(double principalAmount, double interestRate, double time) {
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.Time = time;
    }

    @Override
    public double getPrincipalAmount() {
        return principalAmount;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public double getTime() {
        return Time;
    }
}
