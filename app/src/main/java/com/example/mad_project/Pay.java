package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Pay extends AppCompatActivity {
    Context context;
    Button payid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        context = this;
        payid= findViewById(R.id.paybt);

        payid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your Payment Sucessfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,Main.class));
            }
        });
    }
}