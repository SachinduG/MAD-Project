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
        fName = findViewById(R.id.SignupName);
        fEmail = findViewById(R.id.SignupEmail);
        fMobile = findViewById(R.id.SignupMobile);
        fAddress = findViewById(R.id.SignupAddress);
        fNic = findViewById(R.id.SignupNic);
        fPassword = findViewById(R.id.SignupPassword);
        fConfirmPassword = findViewById(R.id.SignupPassword2);
        SignIn = findViewById(R.id.tvLogin);
        SignUp = findViewById(R.id.btnSignUp);

        SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String MobilePattern = "[0-9]{10}";
                String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (fName.getText().toString().equals(null)){
                    fName.setError("Enter the Name");

                }else if(fEmail.getText().toString().equals(null)){
                    fEmail.setError("Enter the Email Address");

                }else if(fNic.getText().toString().equals(null)){
                    fNic.setError("Enter the NIC Number");

                }else if(fMobile.getText().toString().equals(null)){
                    fMobile.setError("Enter the Mobile Number");

                }else if(fAddress.getText().toString().equals(null)){
                    fAddress.setError("Enter the Address");

                }else if(fPassword.getText().toString().equals(null)){
                    fPassword.setError("Enter the Password");

                }else if(fConfirmPassword.getText().toString().equals(null)) {
                    fConfirmPassword.setError("Enter the Confirm Password!");

                }else if(!fPassword.getText().toString().equals(fConfirmPassword.getText().toString())) {
                    fPassword.setError("Passwords do not match");

                } else if (fPassword.getText().toString().length() < 6) {
                    fPassword.setError("Password must be => 6 characters!");

                } else if (fMobile.getText().toString().length() < 10) {
                    fMobile.setError("Mobile Number must be => 10 numbers!");

                }else if(fNic.getText().toString().length() < 10) {
                    fNic.setError("NIC Number must be => 10 characters!");

                }else if(fEmail.getText().toString().matches(EmailPattern)){
                    fEmail.setError("Invalid Email Address!");

                }else if(fMobile.getText().toString().matches(MobilePattern)){
                    fMobile.setError("Invalid Mobile Number!");

                }else {
                    Boolean Checkemail = db.chkemail(fEmail.getText().toString());

                    if (Checkemail = true) {
                        Boolean insert = db.insert(fName.getText().toString(),fEmail.getText().toString(),fMobile.getText().toString(),fNic.getText().toString(),fAddress.getText().toString(),fPassword.getText().toString());

                        if(insert == true) {
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Main.class);
                            startActivity(intent);
                            clearControls();

                        }else{
                            Toast.makeText(getApplicationContext(), "Registeration Unsuccessfull", Toast.LENGTH_SHORT).show();
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

    private void clearControls(){
        fName.setText("");
        fEmail.setText("");
        fMobile.setText("");
        fNic.setText("");
        fAddress.setText("");
        fPassword.setText("");
        fConfirmPassword.setText("");
    }
}