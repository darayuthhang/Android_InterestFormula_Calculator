package com.darayuth.compoundinterest.contract;

public interface InterestFormulaContract {

    public interface view{
        void displayCompoundInterest(double result);
    }
    public interface presenter{
        double calculateCompoundInterest(double pricipal, double rate, double numberOfTimes, double Time );
        double calculateSimpleInterest(double principalAmount, double interestRate, double time );
        double calculateContinousCompoundInterest(double principalAmount, double interestRate, double time );
    }


}
