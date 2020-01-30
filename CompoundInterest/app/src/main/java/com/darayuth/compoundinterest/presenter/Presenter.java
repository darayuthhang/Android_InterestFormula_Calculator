package com.darayuth.compoundinterest.presenter;

import android.util.Log;
import android.widget.Toast;

import com.darayuth.compoundinterest.contract.InterestFormulaContract;
import com.darayuth.compoundinterest.model.CompoundInterest;
import com.darayuth.compoundinterest.model.ContinousInterest;
import com.darayuth.compoundinterest.model.Interest;
import com.darayuth.compoundinterest.model.SimpleInterest;

public class Presenter implements InterestFormulaContract.presenter {
    private static final String TAG = "Presenter";
    private InterestFormulaContract.view view;
    private Interest compoundInterest, simpleInterest, continousInterest;

    public Presenter(){};
    public Presenter(InterestFormulaContract.view view){
        this.view = view;
    }


    @Override
    public double calculateCompoundInterest(double pricipal, double interestRate, double numberOfTimes, double Time) {
        compoundInterest = new CompoundInterest(pricipal, interestRate, numberOfTimes, Time);
        double middle = ((compoundInterest.getInterestRate() / 100) / numberOfTimes) + 1 ;
        double exponent = numberOfTimes * compoundInterest.getTime();
        double result = Math.pow(middle, exponent) * compoundInterest.getPrincipalAmount();
        Log.i(TAG, "calculateCompoundInterest: "+result);
        this.view.displayCompoundInterest(result);
        return result;

    }

    @Override
    public double calculateSimpleInterest(double principalAmount, double interestRate, double time) {
        simpleInterest = new SimpleInterest(principalAmount, interestRate, time);
        double result = (simpleInterest.getInterestRate() / 100) * simpleInterest.getPrincipalAmount() * simpleInterest.getTime();

        this.view.displayCompoundInterest(result);
        return result;
    }

    @Override
    public double calculateContinousCompoundInterest(double principalAmount, double interestRate, double time) {
        continousInterest = new ContinousInterest(principalAmount, interestRate, time);
        double eulerNumber = 2.71828;

        double pTimesE = (compoundInterest.getPrincipalAmount() * eulerNumber);
        double exponent = compoundInterest.getTime() * compoundInterest.getInterestRate();

        double futureValue = Math.pow(pTimesE, exponent);

        this.view.displayCompoundInterest(futureValue);
        return futureValue;

    }
}
