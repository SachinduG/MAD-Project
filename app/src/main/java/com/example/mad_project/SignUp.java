package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText fName, fEmail, fMobile, fAddress, fNic, fPassword, fConfirmPassword;
    Button SignUp;
    TextView SignIn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DatabaseHelper(this);
        fName = findViewById(R.id.name);
        fEmail = findViewById(R.id.email);
        fMobile = findViewById(R.id.mobile);
        fAddress = findViewById(R.id.address);
        fNic = findViewById(R.id.nic);
        fPassword = findViewById(R.id.password);
        fConfirmPassword = findViewById(R.id.password2);
        SignIn = findViewById(R.id.tvLogin);
        SignUp = findViewById(R.id.btnSignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    if (fName.getText().toString().equals("") || fEmail.getText().toString().equals("") || fNic.getText().toString().equals("") || fMobile.getText().toString().equals("") || fAddress.getText().toString().equals("") || fPassword.getText().toString().equals("") || fConfirmPassword.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "All Fields must be filled!", Toast.LENGTH_LONG).show();
                    } else if (!fPassword.getText().toString().equals(fConfirmPassword.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_LONG).show();
                    } else if (fPassword.getText().toString().length() < 6) {
                        Toast.makeText(getApplicationContext(), "Password must be => 6 characters!", Toast.LENGTH_LONG).show();
                    } else if (fMobile.getText().toString().length() < 10) {
                        Toast.makeText(getApplicationContext(), "Mobile number must be => 10 characters!", Toast.LENGTH_LONG).show();
                    }else {
                        Boolean chkemail = db.chkemail(fEmail.getText().toString());
                        if (chkemail = true) {
                            Boolean insert = db.insert(fName.getText().toString(),fEmail.getText().toString(),fMobile.getText().toString(),fNic.getText().toString(),fAddress.getText().toString(),fPassword.getText().toString());
                            if(insert==true) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), Main.class);
                                startActivity(intent);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Email Already Exists!!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }

        });

        SignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignIn.class));
            }
        });
    }
}