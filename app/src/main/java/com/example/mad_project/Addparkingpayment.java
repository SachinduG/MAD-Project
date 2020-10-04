package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Addparkingpayment extends AppCompatActivity {
    EditText numid;
    Button btnamt;
    TextView amtid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparkingpayment);
        numid= findViewById(R.id.numid);
        btnamt = findViewById(R.id.btnamt);
        amtid = findViewById(R.id.amntid);



btnamt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(numid.getText().length()==0){
            Toast.makeText(getApplicationContext(),"Please Enter a Value",Toast.LENGTH_SHORT).show();
            return;
        }
        int amount = Integer.parseInt(numid.getText().toString());
        amtid.setText("Amount Is:" +String.valueOf(getamount(amount))+"Rs.");

    }
});


    }


public static int  getamount(int amount){

        
        if(amount>0 && amount <= 2){
            return amount * 50;
        }else{
            return amount * 100;
        }

}




}
