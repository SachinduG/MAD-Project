package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.DBHandler;

public class history extends AppCompatActivity {

    TextView name,email,nic,mobile;

    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        name = findViewById(R.id.buttonID);
        email = findViewById(R.id.HistoryEmail);
        nic = findViewById(R.id.HistoryNIC);
        mobile = findViewById(R.id.Historymobile);


        search = findViewById(R.id.HistorySearch);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(name.getText().toString());

                if(user.isEmpty()){

                    Toast.makeText(history.this, "not found", Toast.LENGTH_SHORT).show();
                    name.setText(null);
                }
                else{
                    Toast.makeText(history.this, "found", Toast.LENGTH_SHORT).show();
                    name.setText(user.get(0).toString());
                    email.setText(user.get(1).toString());
                    nic.setText(user.get(2).toString());
                    mobile.setText(user.get(3).toString());

                }
            }
        });
            }
    }

