package com.darayuth.compoundinterest.contract;

public interface InterestFormulaContract {

    public interface view{
        void displayCompoundInterest(double result);
    }
    public interface presenter{
        void calculateCompoundInterest(double pricipal, double rate, double numberOfTimes, double Time );
        void calculateSimpleInterest(double principalAmount, double interestRate, double time );
        void calculateContinousCompoundInterest(double principalAmount, double interestRate, double time );
    }


}
