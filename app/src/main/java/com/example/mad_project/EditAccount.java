package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAccount extends AppCompatActivity {

    EditText fName, fEmail, fMobile, fAddress, fNic;
    Button Update,Cancel;
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
        Update = findViewById(R.id.Update);
        Cancel = findViewById(R.id.cancel);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fName.getText().toString().equals("") || fEmail.getText().toString().equals("") || fNic.getText().toString().equals("") || fMobile.getText().toString().equals("") || fAddress.getText().toString().equals("")  ) {
                    Toast.makeText(getApplicationContext(), "All Fields must be filled!", Toast.LENGTH_LONG).show();
                } else  (fMobile.getText().toString().length() < 10) {
                    Toast.makeText(getApplicationContext(), "Mobile number must be => 10 characters!", Toast.LENGTH_LONG).show();
                }else {
                    Boolean chkemail = db.chkemail(fEmail.getText().toString());
                    if (chkemail = true) {

    }
}