package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;

public class EditPArking extends AppCompatActivity {

    EditText  email,town,address,mobile,description;
    Button Update, delete,view;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_p_arking);


        //  databaseHelper = new DatabaseHelper(this);
        // db = databaseHelper.getWritableDatabase();
        db = new DatabaseHelper(this);
        email = findViewById(R.id.emid);
        town = findViewById(R.id.townid);
        address = findViewById(R.id.addid);
        mobile = findViewById(R.id.mobile);
        description = findViewById(R.id.descrip);

        Update = findViewById(R.id.edtbtn);
        delete = findViewById(R.id.adddelt);
        view = findViewById(R.id.button8);


        // final Intent previousIntent = getIntent();
        //String email = previousIntent.getStringExtra("email");
        // final CustomerProfile customerProfile = databaseHelper.findCustomer(email, db);












        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";

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


                } else if (mobile.length() < 10) {
                    mobile.setError("Mobile Number must be => 10 numbers!");


                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Invalid Email Address!");

                } else if (mobile.getText().toString().matches(mobilePattern)) {
                    mobile.setError("Invalid Mobile Number!");

                } else {

                    db.parkupdate( email.getText().toString(),town.getText().toString(),address.getText().toString(),mobile.getText().toString(), description.getText().toString());

                    Toast.makeText(EditPArking.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete Your Park");
                builder.setMessage("Are you sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Email = email.getText().toString();
                        db.parkdelete(Email);

                        Toast.makeText(EditPArking.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}