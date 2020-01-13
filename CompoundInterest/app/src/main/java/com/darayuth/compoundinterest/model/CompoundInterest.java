package com.darayuth.compoundinterest.model;

public class CompoundInterest implements Interest {

    private double pricipalAmount, numberOfTime, interestRate, time;


    public CompoundInterest(){};

    public CompoundInterest(double pricipalAmount, double interestRate,  double numberOfTime, double time) {
        this.pricipalAmount = pricipalAmount;
        this.numberOfTime = numberOfTime;
        this.interestRate = interestRate;
        this.time = time;
    }

    public double getNumberOfTime() {
        return numberOfTime;
    }

    @Override
    public double getPrincipalAmount() {
        return pricipalAmount;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public double getTime() {
        return time;
    }
}
