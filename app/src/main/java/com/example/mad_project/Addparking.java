package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.model.Park;

public class Addparking extends AppCompatActivity {

    EditText email,town,address,mobile,description;

    TextView rateperhour;
    DbHandler dbHandler;
    Button add;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparking);


        email = findViewById(R.id.emid);
        town = findViewById(R.id.townid);
        address = findViewById(R.id.addid);
        mobile = findViewById(R.id.mobile);
        description = findViewById(R.id.descrip);

        rateperhour =findViewById(R.id.txtaddid);
        add = findViewById(R.id.addparkid);

        context = this;
        dbHandler = new DbHandler(context);


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";

                String useremail = email.getText().toString();
                String usertown = town.getText().toString();
                String useraddress =  address.getText().toString();
                String usermobile = mobile.getText().toString();
                String userdescrip = description.getText().toString();


                if (useremail.isEmpty()) {
                    email.setError("Enter the Email Address");

                } else if (usertown.isEmpty()) {
                    town.setError("Enter your Town");

                } else if (useraddress.isEmpty()) {
                    address.setError("Enter the Address");

                } else if (usermobile.isEmpty()) {
                    mobile.setError("Enter the Mobile Number");


                } else if (userdescrip.isEmpty()) {
                    description.setError("Enter a description");


                } else if (usermobile.length() < 10) {
                    mobile.setError("Mobile Number must be => 10 numbers!");


                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Invalid Email Address!");

                } else if (mobile.getText().toString().matches(mobilePattern)) {
                    mobile.setError("Invalid Mobile Number!");

                } else {

                    Park park = new Park(useremail,usertown,useraddress,usermobile,userdescrip);
                    dbHandler.addpark(park);
                        Toast.makeText(getApplicationContext(), "Your Park added Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Addparkingpayment.class);
                        startActivity(intent);

                    }

                }




        });


}

}

