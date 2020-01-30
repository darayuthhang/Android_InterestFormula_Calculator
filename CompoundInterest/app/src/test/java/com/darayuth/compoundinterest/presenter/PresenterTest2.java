package com.darayuth.compoundinterest.presenter;

import com.darayuth.compoundinterest.MainActivity;
import com.darayuth.compoundinterest.contract.InterestFormulaContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class PresenterTest2 {
    DecimalFormat df2 =  new DecimalFormat("#.##");;
    @Mock
    Presenter presenter = new Presenter();
    @Mock
    InterestFormulaContract.view View;
    @Before
    public void init(){
        View = Mockito.mock( InterestFormulaContract.view.class);
        presenter = new Presenter(View);
    }
    @Test
    public void calculateCompoundInterest() {
        double result = presenter.calculateCompoundInterest(1, 2, 3, 4);
        String value = String.valueOf(df2.format(result));
        assertEquals("1.08", value);
    }

    @Test
    public void calculateSimpleInterest() {
    }

    @Test
    public void calculateContinousCompoundInterest() {
    }
}