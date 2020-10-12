package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BOOKPayment extends AppCompatActivity {



    EditText n1,b1,b2,b3,b4;
    TextView cal;
    Button tot,list;
    int convertIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_o_o_k_payment);



        list = findViewById(R.id.button11);

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),BookuserList.class);
                startActivity(intent);
            }
        });


    }


    public  void add(View view){


        n1 = findViewById(R.id.hoursme);
        cal = findViewById(R.id.addme);
        tot = findViewById(R.id.calme);


        if(n1.getText().length() == 0) {

            Toast.makeText(this, "please enter a number", Toast.LENGTH_SHORT).show();
            return;

        }


            String num1 = n1.getText().toString();

            convertIN = Integer.parseInt(num1);


           // int ammount = convertIN * 100 ;


            cal.setText(   " RS. "  +"  " +(bookpaymentCal(convertIN)));






    }

    public  static  float bookpaymentCal(float number){

        return (number * 100);

    }
}