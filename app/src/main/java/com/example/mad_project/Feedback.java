package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Feedback extends AppCompatActivity {
    EditText name, email, message;
    Button btnview, btnsend;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        db = new DatabaseHelper(this);
        name = findViewById(R.id.FD_name);
        email = findViewById(R.id.FD_email);
        message = findViewById(R.id.FD_message);
        btnsend = findViewById(R.id.btnFD);
        btnview = findViewById(R.id.btnViewFeedback);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String FullName = name.getText().toString().trim();
                final String Email = email.getText().toString().trim();
                final String Message = message.getText().toString().trim();

                if (FullName.isEmpty()){
                    name.setError("Fill your Name");

                }else if(Email.isEmpty()){
                    email.setError("Fill your Email Address");

                }else if(Message.isEmpty()){
                    message.setError("Fill your Message");

                }else {
                    Boolean insert = db.insert(FullName, Email, Message);

                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Sent Feedback Successfully", Toast.LENGTH_SHORT).show();
                        btnsend.setEnabled(true);
                        btnview.setEnabled(true);

                    }else{
                        Toast.makeText(getApplicationContext(), "Cannot Sent Feedback", Toast.LENGTH_SHORT).show();
                    }
                }

                btnview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new AlertDialog.Builder(Feedback.this)
                                .setTitle("Your Feedback:")
                                .setMessage("Full Name :- " + FullName + "\n\nEmail Address :- " + Email + "\n\nMessage :- " + Message)
                                .show();
                    }
                });
            }
        });
    }
}