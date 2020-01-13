package com.darayuth.compoundinterest;

import android.content.Intent;
import android.os.Bundle;

import com.darayuth.compoundinterest.activities.interestResult;
import com.darayuth.compoundinterest.contract.InterestFormulaContract;
import com.darayuth.compoundinterest.presenter.Presenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class contiuousInterest extends AppCompatActivity implements  AdapterView.OnItemSelectedListener, View.OnClickListener, InterestFormulaContract.view {


    private static DecimalFormat df2 =  new DecimalFormat("#.##");;
    private EditText EprinciPal, Erate, ETime;
    private String formulaLists;
    private double princiPal, Rate, Time;
    private Button submitBtn;
    private InterestFormulaContract.presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contiuous_interest);

        //create presenter instance
        mPresenter = new Presenter(this);
        //spinner compoundInterest
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interestSpinner3, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        //setIdd to all the input boxes.
        init();
    }
    public void init(){
        EprinciPal = (EditText) findViewById(R.id.editTextP);
        Erate = (EditText) findViewById(R.id.editTextR);
        ETime = (EditText) findViewById(R.id.editTextT);
        // set id , and event to the submit button ;
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int idView = v.getId();
        //if the input is empty, secondActivity cannot be switch
        if (EprinciPal.getText().toString().equals("") ||
                Erate.getText().toString().equals("") ||
                ETime.getText().toString().equals("")) {
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
        }else if(R.id.submitBtn == idView && formulaLists.equalsIgnoreCase("ContinuousCompoundInterest")){
            try{
                convertAllTheInputIntoDouble();
                //pass the all the input parameter to presenter.
                mPresenter.calculateSimpleInterest(princiPal, Rate, Time);
            }catch (NumberFormatException e){
                Toast.makeText(this, "Inputs cannot be letters", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void convertAllTheInputIntoDouble(){
        princiPal =Double.parseDouble(EprinciPal.getText().toString());
        Rate = Double.parseDouble(Erate.getText().toString());
        Time = Double.parseDouble(ETime.getText().toString());
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        formulaLists = parent.getItemAtPosition(position).toString();
        if(formulaLists.equalsIgnoreCase("simpleinterest")){
            Intent intent = new Intent(this, simpleInterest.class);
            startActivity(intent);
        }else if(formulaLists.equalsIgnoreCase("compoundInterest")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void displayCompoundInterest(double result) {
        String results = String.valueOf(df2.format(result));
        Intent intent = new Intent(this, interestResult.class);
        intent.putExtra("results", results);
        intent.putExtra("spinnerPicking", "simpleInterest");
        startActivity(intent);
    }
}
