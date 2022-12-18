package com.route.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultText = findViewById(R.id.resultText);
    }

    public void onDigitClick(View view){
        if (!equalResult.equals("")){
            resultText.setText("");
            equalResult="";
            Log.e("CalculatorActivity#", "onDigitClick: IF CONDITION  !equalResult.equals");
        }

        Button clickedDigit = (Button) view;
        if( clickedDigit.getText().equals(".")&&resultText.getText().toString().contains(".")){
            Log.e("CalculatorActivity#", "onDigitClick: IF CONDITION 2222222");
            return;

        }
        if(resultText.getText().toString().equals("") && clickedDigit.getText().equals(".")){
            resultText.append("0");
        }
        resultText.append(clickedDigit.getText());
        Log.e("CalculatorActivity#", "onDigitClick: APPEND");

    }

    String lhs ="" ;
    String operator ="" ;
    String equalResult ="";

    public void onOperatorClick(View view){
        Button clickedOperator = (Button) view;
        if(resultText.getText().toString().equals("") && !operator.equals("√")){
            operator= clickedOperator.getText().toString();
            return;
        }
        if(operator.isEmpty()){
            lhs= resultText.getText().toString();
            operator =clickedOperator.getText().toString();
            if(operator.equals("√")){
//                Log.e("CalculatorActivity#", "onOperatorClick: IF CONDITION");
                sqrt();
            }
            resultText.setText("");
        }
        else{
            if(operator.equals("√")){
                sqrt();
            }
            lhs = calculate(lhs, operator,  resultText.getText().toString());
            operator =clickedOperator.getText().toString();
            resultText.setText("");

        }

        Log.e("CalculatorActivity#", "onOperatorClick: lhs:"+lhs +"\n Operator"+ operator);
    }
     String calculate(String lhs, String operator, String rhs){
         Double result;

        Double leftHandSide = Double.parseDouble(lhs);
         if(operator.equals("√")){
             result =Math.sqrt(leftHandSide);
             return result.toString();
         }
        Double rightHandSide = Double.parseDouble(rhs);
         if(operator.equals("+")){
             result =leftHandSide + rightHandSide;
            return result.toString();
         }
         else if(operator.equals("-")){
             result =leftHandSide - rightHandSide;
             return result.toString();
         }
         else if(operator.equals("x")){
             result =leftHandSide * rightHandSide;
             return result.toString();
         }
         else if(operator.equals("/")){
             result =leftHandSide / rightHandSide;
             return result.toString();
         }
         else{
             result =  Math.pow(leftHandSide,rightHandSide);
             return result.toString();
         }
     }

     public void sqrt(){
         if(lhs=="" && resultText.getText().toString().equals("")){
             operator="";
             return;

         }
         else {
//             Log.e("CalculatorActivity#", "onOperatorClick: IF CONDITION 3333---" + lhs);
             Double sqrt= Math.sqrt(Double.parseDouble(resultText.getText().toString()));
             lhs =  sqrt.toString();
//             Log.e("CalculatorActivity#", "SQRT" + lhs);
             operator="";
         }

     }
    public void clear(View view){
        operator="";
        lhs="";
        resultText.setText("");
        equalResult="";
    }

    public void onEqualClick(View view){
        if(resultText.getText().toString().equals("")||operator.equals("")){
            return;
        }
        String result = calculate(lhs, operator,  resultText.getText().toString());
        resultText.setText(result);
        equalResult=result;

        lhs ="";
        operator="";

    }

}