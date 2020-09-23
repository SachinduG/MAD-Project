package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Int4;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Database.DBHandler;

public class BookingProfileManagement extends AppCompatActivity {


    TextView name,email,nic,mobile;
    Button bookUpdate,search,Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_profile_management);




        name = findViewById(R.id.EDITBnamee);
        email = findViewById(R.id.EDITBEmail);
        nic = findViewById(R.id.EDITBNic);
        mobile = findViewById(R.id.Editmobile);

        bookUpdate = findViewById(R.id.EDitUpdatebtn);
        search = findViewById(R.id.buttonsearchj);
        Delete = findViewById(R.id.delete);

        bookUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler (getApplicationContext());

                Boolean satus = dbHandler.UpdateBookDetails(name.getText().toString(),email.getText().toString(),nic.getText().toString(),mobile.getText().toString());

                if(satus)
                {


                    Toast.makeText(BookingProfileManagement.this, " upated sucessfully", Toast.LENGTH_SHORT).show();
                }

                else {

                    Toast.makeText(BookingProfileManagement.this, "not updated", Toast.LENGTH_SHORT).show();

                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(name.getText().toString());

                if(user.isEmpty()){

                    Toast.makeText(BookingProfileManagement.this, "no user", Toast.LENGTH_SHORT).show();
                    name.setText(null);
                }
                else{
                    Toast.makeText(BookingProfileManagement.this, "found user"+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    name.setText(user.get(0).toString());
                    email.setText(user.get(1).toString());
                    nic.setText(user.get(2).toString());
                    mobile.setText(user.get(3).toString());

                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                dbHandler.deleteinfro(name.getText().toString());


                name.setText(null);
                email.setText(null);
                nic.setText(null);
                mobile.setText(null);

                Toast.makeText(BookingProfileManagement.this, "sucessfully deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),BOOKPayment.class);
                startActivity(intent);

            }
        });




    }
}