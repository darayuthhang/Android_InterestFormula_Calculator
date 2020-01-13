package com.darayuth.compoundinterest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.darayuth.compoundinterest.R;

import java.text.DecimalFormat;

public class interestResult extends AppCompatActivity {

    private EditText resultText;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activty);
        init();


        Intent intent = getIntent();
        String results = intent.getStringExtra("results");
        String spinnerPicking = intent.getStringExtra("spinnerPicking");

        String choice = menu(spinnerPicking);
        displayResult(results, choice);
    }

    public String menu(String choice){
        if(choice.equalsIgnoreCase("compoundInterest")){
            return "FutureValue =  ";
        }else if(choice.equalsIgnoreCase("simpleInterest")){
            return "Interest = ";
        }else if(choice.equalsIgnoreCase("ContinuousCompoundInterest")){
            return "FutureValue = ";
        }
      return null;
    }
    //find all the id of the UIs Views.
    public void init(){
        resultText = (EditText) findViewById(R.id.resultText);
        textView = (TextView) findViewById(R.id.textView);
    }


    public void displayResult(String result, String choice){
        resultText.setText(result);
        textView.setText(choice);
    }


}
