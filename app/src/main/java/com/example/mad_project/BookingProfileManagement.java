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

        Intent intent = getIntent();

        String num1 = intent.getStringExtra("name");
        String num2 = intent.getStringExtra("email");
        String num3 = intent.getStringExtra("nic");
        String num4 = intent.getStringExtra("mobile");

        name.setText(num1);
        email.setText(num2);
        nic.setText(num3);
        mobile.setText(num4);

        bookUpdate.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";


                if (!email.getText().toString().trim().matches(emailPattern)) {

                    email.setError("invalid email address");
                } else if (mobile.getText().toString().trim().matches(mobilePattern)) {

                    mobile.setError("invalid mobile");
                } else if (mobile.getText().toString().trim().matches(mobilePattern)) {

                    mobile.setError("invalid mobile");
                } else if (mobile.length() < 10) {
                    mobile.setError("mobile must be 10 number ");
                } else {



                    DBHandler dbHandler = new DBHandler(getApplicationContext());


                    Boolean satus = dbHandler.UpdateBookDetails(name.getText().toString(), email.getText().toString(), nic.getText().toString(), mobile.getText().toString());

                    Intent intent= new Intent(getApplicationContext(),BOOKPayment.class);
                    startActivity(intent);

                    if (satus) {


                        Toast.makeText(BookingProfileManagement.this, " upated sucessfully", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(BookingProfileManagement.this, "not updated", Toast.LENGTH_SHORT).show();



                    }
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