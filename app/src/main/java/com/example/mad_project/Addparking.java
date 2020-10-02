package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Addparking extends AppCompatActivity {

    EditText email,town,address,mobile,description;
    Button done;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparking);
        db = new DatabaseHelper(this);
        email = findViewById(R.id.emid);
        town = findViewById(R.id.townid);
        address = findViewById(R.id.addid);
        mobile = findViewById(R.id.mobile);
        description = findViewById(R.id.descrip);
        done = findViewById(R.id.button);

        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";

                String Email = email.getText().toString().trim();
                String Town = town.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Mobile = mobile.getText().toString().trim();
                String Description = description.getText().toString().trim();


                if (Email.isEmpty()) {
                    email.setError("Enter the Email Address");

                } else if (Town.isEmpty()) {
                    town.setError("Enter your Town");

                } else if (Address.isEmpty()) {
                    address.setError("Enter the Address");

                } else if (Mobile.isEmpty()) {
                    mobile.setError("Enter the Mobile Number");


                } else if (Description.isEmpty()) {
                    description.setError("Enter a description");


                } else if (Mobile.length() < 10) {
                    mobile.setError("Mobile Number must be => 10 numbers!");


                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Invalid Email Address!");

                } else if (mobile.getText().toString().matches(mobilePattern)) {
                    mobile.setError("Invalid Mobile Number!");

                } else {


                    Boolean insert = db.parkinsert(Email, Town, Address, Mobile, Description);

                    if (insert.equals(true)) {

                        Toast.makeText(getApplicationContext(), "Your Park added Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        startActivity(intent);
                        clearControls();
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }


                }


            }

        });
    }
    private void clearControls() {
        email.setText("");
        town.setText("");
        address.setText("");
        mobile.setText("");
        description.setText("");


    }


}





