package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BOOKPayment extends AppCompatActivity {



    EditText n1,b1,b2,b3,b4;
    TextView cal;
    Button tot;
    int convertIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_o_o_k_payment);









    }


    public  void add(View view){


        n1 = findViewById(R.id.hoursme);
        cal = findViewById(R.id.addme);
        tot = findViewById(R.id.calme);


        String num1 = n1.getText().toString();

        convertIN = Integer.parseInt(num1);

        int ammount = convertIN * 100 ;




        cal.setText( convertIN + " charge "  +" = " +(ammount));


    }
}