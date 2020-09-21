package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Database.DBHandler;

public class BookingHome extends AppCompatActivity {


    TextView name,email,nic,mobile;
    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_home);


        name = findViewById(R.id.BookName);
        email = findViewById(R.id.BookEmail);
        nic = findViewById(R.id.BookNIC);
        mobile = findViewById(R.id.BookMobile);

        book = findViewById(R.id.BookSubmit1);


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                long newdetails = dbHandler.Addinformation(name.getText().toString(),email.getText().toString(),nic.getText().toString(),mobile.getText().toString());

                Toast.makeText(BookingHome.this, "added sucessfully"+newdetails, Toast.LENGTH_SHORT).show();




            }
        });


    }
}