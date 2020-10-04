package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Database.DBHandler;

public class SearchUsersBooking extends AppCompatActivity {

    Button update,search;
    EditText name,email,nic,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users_booking);


        search = findViewById(R.id.searchdetails);
        name = findViewById(R.id.searchBookname);
        email = findViewById(R.id.searchBookemail);
        nic = findViewById(R.id.searchBookNIC);
        mobile = findViewById(R.id.searchBookmobile);
        update = findViewById(R.id.searchupdated);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfosearch(nic.getText().toString());

                if(user.isEmpty()){

                    Toast.makeText(SearchUsersBooking.this, "no user", Toast.LENGTH_SHORT).show();

                    nic.setText(null);
                }
                else{
                    Toast.makeText(SearchUsersBooking.this, "found user"+user.get(0), Toast.LENGTH_SHORT).show();
                    nic.setText(user.get(0).toString());
                    name.setText(user.get(1).toString());
                    email.setText(user.get(2).toString());
                    mobile.setText(user.get(3).toString());

                }
            }
        });



        update.setOnClickListener(new View.OnClickListener() {



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


                        Toast.makeText(SearchUsersBooking.this, "updated", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(SearchUsersBooking.this, "not updated", Toast.LENGTH_SHORT).show();



                    }
                }
            }
        });

    }
}