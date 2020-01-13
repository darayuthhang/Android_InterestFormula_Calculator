package com.darayuth.compoundinterest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.darayuth.compoundinterest.activities.interestResult;
import com.darayuth.compoundinterest.contract.InterestFormulaContract;
import com.darayuth.compoundinterest.presenter.Presenter;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, InterestFormulaContract.view {


    private static DecimalFormat df2 =  new DecimalFormat("#.##");;
    private EditText EprinciPal, Erate, EnumberOfTimes, ETime;
    private String formulaLists;
    private double princiPal, Rate, numberOfTimes, Time;
    private Button submitBtn;
    private InterestFormulaContract.presenter mPresenter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create presenter instance
        mPresenter = new Presenter(this);
        //spinner compoundInterest
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interestSpinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        //setIdd to all the input boxes.
        init();
    }
    public void init(){
        EprinciPal = (EditText) findViewById(R.id.editTextP);
        Erate = (EditText) findViewById(R.id.editTextR);
        EnumberOfTimes = (EditText) findViewById(R.id.editTextN);
        ETime = (EditText) findViewById(R.id.editTextT);
        // set id , and event to the submit button ;
        submitBtn = (Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
         formulaLists = parent.getItemAtPosition(position).toString();
        if(formulaLists.equalsIgnoreCase("simpleinterest")){
            Intent intent = new Intent(this, simpleInterest.class);
            startActivity(intent);
        } else if(formulaLists.equalsIgnoreCase("ContinuousCompoundInterest")){
            Intent intent = new Intent(this, contiuousInterest.class);
            startActivity(intent);
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();
        //if the input is empty, secondActivity cannot be switch
        if (EprinciPal.getText().toString().equals("") ||
                Erate.getText().toString().equals("") ||
                EnumberOfTimes.getText().toString().equals("") ||
                ETime.getText().toString().equals("")) {
                Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
        }else if(R.id.submitBtn == idView && formulaLists.equalsIgnoreCase("compoundInterest")){
            try{
                convertAllTheInputIntoDouble();
                //pass the all the input parameter to presenter.
                mPresenter.calculateCompoundInterest(princiPal, Rate, numberOfTimes, Time);
            }catch (NumberFormatException e){
                Toast.makeText(this, "Inputs cannot be letter", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }
    public void convertAllTheInputIntoDouble(){
            princiPal =Double.parseDouble(EprinciPal.getText().toString());
            Rate = Double.parseDouble(Erate.getText().toString());
            numberOfTimes = Double.parseDouble(ETime.getText().toString());
            Time = Double.parseDouble(ETime.getText().toString());
    }

    //display the amount, and switch to the second activity
    @Override
    public void displayCompoundInterest(double result) {
        String results = String.valueOf(df2.format(result));
        Intent intent = new Intent(this, interestResult.class);
        intent.putExtra("results", results);
        intent.putExtra("spinnerPicking", "compoundInterest");
        startActivity(intent);
    }
}
