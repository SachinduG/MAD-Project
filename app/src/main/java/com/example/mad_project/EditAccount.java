package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;

public class EditAccount extends AppCompatActivity {

    EditText fName, fEmail, fMobile, fAddress, fNic, fPassword, fConfirmPassword;
    Button Update, delete;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        db = new DatabaseHelper(this);
        fName = findViewById(R.id.name);
        fEmail = findViewById(R.id.email);
        fMobile = findViewById(R.id.mobile);
        fAddress = findViewById(R.id.address);
        fNic = findViewById(R.id.nic);
        fPassword = findViewById(R.id.password);
        Update = findViewById(R.id.Update);
        delete = findViewById(R.id.button7);


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fName.getText().toString().equals("")) {
                    fName.setError("Enter the Name");

                } else if (fEmail.getText().toString().equals("")) {
                    fEmail.setError("Enter the Email Address");

                } else if (fNic.getText().toString().equals("")) {
                    fNic.setError("Enter the NIC Number");

                } else if (fMobile.getText().toString().equals("")) {
                    fMobile.setError("Enter the Mobile Number");

                } else if (fAddress.getText().toString().equals("")) {
                    fAddress.setError("Enter the Address");

                } else if (fPassword.getText().toString().equals("")) {
                    fPassword.setError("Enter the Password");


                } else if (fPassword.getText().toString().length() < 6) {
                    fPassword.setError("Password must be => 6 characters!");

                } else if (fMobile.getText().toString().length() < 10) {
                    fMobile.setError("Mobile Number must be => 10 characters!");

                } else {
                    db.update(fName.getText().toString(), fEmail.getText().toString(), fNic.getText().toString(), fMobile.getText().toString(), fAddress.getText().toString(), fPassword.getText().toString());

                    Toast.makeText(EditAccount.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete Your Account");
                builder.setMessage("Are you sure to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String Email = fEmail.getText().toString();
                        db.delete(Email);

                        Toast.makeText(EditAccount.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SignUp.class));
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




