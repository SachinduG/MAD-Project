package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import com.example.mad_project.model.Park;

public class EditPArking extends AppCompatActivity {

    EditText  email,town,address,mobile,description;
    Button Update;
    Context context;
    DbHandler dbHandler;
    TextView rateperhour;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_p_arking);



        context = this;
        dbHandler = new DbHandler(context);

        email = findViewById(R.id.edtemail);
        town = findViewById(R.id.edttown);
        address = findViewById(R.id.edtaddress);
        mobile= findViewById(R.id.edtmob);
        description=findViewById(R.id.edtdis);
        rateperhour =findViewById(R.id.txtedt);
        Update = findViewById(R.id.edtbtn);

        final String id = getIntent().getStringExtra("id");
        Park park = dbHandler.getsinglepark(Integer.parseInt(id));

        email.setText(park.getEmail());
        town.setText(park.getTown());
        address.setText(park.getAddress());
        mobile.setText(park.getMobile());
        description.setText(park.getDescription());















        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";

                String edemail  = email.getText().toString();
                String edaddress  = address.getText().toString();
                String edtown = town.getText().toString();
                String edmobile  = mobile.getText().toString();
                String eddescrip  = description.getText().toString();


                if (email.toString().equals("")) {
                    email.setError("Enter the Email Address");

                } else if (town.toString().equals("")) {
                   town.setError("Enter the Town");

                } else if (address.toString().equals("")) {
                    address.setError("Enter the  Address");

                } else if (mobile.toString().equals("")) {
                   mobile.setError("Enter the mobile");


                } else if (description.toString().equals("")) {
                    description.setError("Enter the description");


                } else if (edmobile.length() < 10) {
                    mobile.setError("Mobile Number must be => 10 numbers!");


                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Invalid Email Address!");

                } else if (mobile.getText().toString().matches(mobilePattern)) {
                    mobile.setError("Invalid Mobile Number!");

                } else {


                    Park park = new Park(Integer.parseInt(id),edemail,edaddress,edtown,edmobile,eddescrip);
                    int state = dbHandler.parkupdate(park);
                    System.out.println(state);
                    Toast.makeText(EditPArking.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context,OwnParkList.class));



                }
            }
        });


    }
}