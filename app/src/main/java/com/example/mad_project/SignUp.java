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

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String mobilePattern = "[a-zA-Z]+";

                String Email = fEmail.getText().toString().trim();
                String FullName = fName.getText().toString().trim();
                String Nic = fNic.getText().toString().trim();
                String Mobile = fMobile.getText().toString().trim();
                String Address = fAddress.getText().toString().trim();
                String Password = fPassword.getText().toString().trim();
                String ConfirmPassword = fConfirmPassword.getText().toString().trim();

                if (FullName.isEmpty()){
                    fName.setError("Enter the Full Name");

                }else if(Email.isEmpty()){
                    fEmail.setError("Enter the Email Address");

                }else if(Nic.isEmpty()){
                    fNic.setError("Enter the NIC Number");

                }else if(Mobile.isEmpty()){
                    fMobile.setError("Enter the Mobile Number");

                }else if(Address.isEmpty()){
                    fAddress.setError("Enter the Address");

                }else if(Password.isEmpty()){
                    fPassword.setError("Enter the Password");

                }else if(ConfirmPassword.isEmpty()) {
                    fConfirmPassword.setError("Enter the Confirm Password!");

                }else if(!Password.contentEquals(ConfirmPassword)) {
                    fPassword.setError("Passwords do not match");

                } else if (Password.length() < 6) {
                    fPassword.setError("Password must be => 6 characters!");

                } else if (Mobile.length() < 10) {
                    fMobile.setError("Mobile Number must be => 10 numbers!");

                }else if(Nic.length() < 10) {
                    fNic.setError("NIC Number must be => 10 characters!");

                }else if(!fEmail.getText().toString().trim().matches(emailPattern)){
                    fEmail.setError("Invalid Email Address!");

                }else if(fMobile.getText().toString().matches(mobilePattern)){
                    fMobile.setError("Invalid Mobile Number!");

                }else {
                    Boolean Checkemail = db.chkemail(fEmail.getText().toString());

                    if (Checkemail.equals(true)) {
                        Boolean insert = db.insert(FullName,Email,Mobile,Nic,Address,Password);

                        if(insert.equals(true)) {
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Main.class);
                            startActivity(intent);
                            clearControls();

                        }else{
                            Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
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