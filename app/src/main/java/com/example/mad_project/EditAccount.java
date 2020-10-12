package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;
import com.example.mad_project.DatabaseHelper;
import com.example.mad_project.CustomerProfile;

public class EditAccount extends AppCompatActivity {

    EditText fName, fEmail, fMobile, fAddress, fNic, fPassword, fConfirmPassword;
    Button Update, delete,view;

    DatabaseHelper db;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);


      //  databaseHelper = new DatabaseHelper(this);
       // db = databaseHelper.getWritableDatabase();
        db = new DatabaseHelper(this);
        fName = findViewById(R.id.name);
        fEmail = findViewById(R.id.email);
        fMobile = findViewById(R.id.mobile);
        fAddress = findViewById(R.id.address);
        fNic = findViewById(R.id.nic);
        fPassword = findViewById(R.id.password);
        fConfirmPassword = findViewById(R.id.password2);
        Update = findViewById(R.id.Update);
        delete = findViewById(R.id.button7);
        view = findViewById(R.id.button8);

        sessionManager = new SessionManager(getApplicationContext());

       // final Intent previousIntent = getIntent();
        //String email = previousIntent.getStringExtra("email");
       // final CustomerProfile customerProfile = databaseHelper.findCustomer(email, db);












        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";

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
                } else if (fConfirmPassword.getText().toString().equals("")) {
                    fConfirmPassword.setError("Enter the confirm Password");
                } else if (fConfirmPassword.getText().toString().length() < 6) {
                    fPassword.setError("Password must be => 6 characters!");

               // }else if(!fPassword.contentEquals(fConfirmPassword)) {
                 //   fPassword.setError("Passwords do not match");

                } else if (fMobile.getText().toString().length() < 10) {
                    fMobile.setError("Mobile Number must be => 10 characters!");

                } else if (fNic.getText().toString().length() < 9) {
                    fNic.setError("Nic Number must be => 9 characters!");

                }else if(!fEmail.getText().toString().trim().matches(emailPattern)){
                    fEmail.setError("Invalid Email Address!");

                }else if(fMobile.getText().toString().matches(mobilePattern)){
                    fMobile.setError("Invalid Mobile Number!");

                } else {
                   db.update(fName.getText().toString(), fEmail.getText().toString(),fMobile.getText().toString(), fNic.getText().toString(), fAddress.getText().toString(), fPassword.getText().toString());

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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(EditAccount.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Email :"+res.getString(1)+"\n");
                    buffer.append("Mobile :"+res.getString(2)+"\n");
                    buffer.append("NIC :"+res.getString(3)+"\n");
                    buffer.append("Address:"+res.getString(4)+"\n");
                    buffer.append("Password :"+res.getString(5)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EditAccount.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}




