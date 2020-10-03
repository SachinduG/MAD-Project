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




            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String mobilePattern = "[a-zA-Z]+";
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());



                if(name.getText().toString().isEmpty()){
                    name.setError("enter the name");


                }else if(email.getText().toString().isEmpty()){
                    email.setError("enter the email");
                }
                else if(nic.getText().toString().isEmpty()){

                    nic.setError("enter the nic");
                }
                else if(mobile.getText().toString().isEmpty()){

                    mobile.setError("enter the mobile");
                }
                else if(!email.getText().toString().trim().matches(emailPattern)){

                    email.setError("invalid email address");
                }
                else if(mobile.getText().toString().trim().matches(mobilePattern)){

                    mobile.setError("invalid mobile");
                }
                else if(mobile.length() < 10){
                    mobile.setError("mobile must be 10 number ");
                }
                else if( nic.length() <= 10)
                {
                    nic.setError("invalid NIC");
                }
                else {






                    long newdetails = dbHandler.Addinformation(name.getText().toString(),email.getText().toString(),nic.getText().toString(),mobile.getText().toString());

                    Intent intent  = new Intent(getApplicationContext(),BookingProfileManagement.class);
                    intent.putExtra("name",name.getText().toString());
                    intent.putExtra("email",email.getText().toString());
                    intent.putExtra("nic",nic.getText().toString());
                    intent.putExtra("mobile",mobile.getText().toString());

                    Toast.makeText(BookingHome.this, "added sucessfully"+newdetails, Toast.LENGTH_SHORT).show();

                    startActivity(intent);


                }







            }
        });


    }


}