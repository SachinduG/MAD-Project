package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    Button Next;
    EditText Email, Pass1, Pass2;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Pass1 = findViewById(R.id.ForgotPassword1);
        Pass2 = findViewById(R.id.ForgotPassword2);
        Next = findViewById(R.id.btnConfirm);
        Email = findViewById(R.id.forgotEmail);
        db = new DatabaseHelper(this);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Password1 = Pass1.getText().toString().trim();
                String Password2 = Pass2.getText().toString().trim();
                String EmailAddress = Email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                Boolean CheckUser = db.checkUser(Email.getText().toString());

                if (CheckUser.equals(false)) {
                    Email.setError("Email Address doesn't exist");

                }else if(!Email.getText().toString().matches(emailPattern)) {
                   Email.setError("Email Address is a not valid one");

                }else if(EmailAddress.isEmpty()){
                    Email.setError("Enter your Email Address");

                }else if(Password1.isEmpty()){
                    Pass1.setError("Enter the New Password");

                }else if(Password2.isEmpty()) {
                    Pass2.setError("Enter the confirm Password");

                }else if(Password1.length() < 6){
                    Pass1.setError("Password must be => 6 characters!");

                } else{
                    if(Password1.contentEquals(Password2)) {
                        db.updatePassword(Email.getText().toString(), Pass1.getText().toString());
                        Toast.makeText(getApplicationContext(), "Password change successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ForgotPassword.this, SignIn.class);
                        startActivity(i);
                        clearControls();

                    }else{
                        Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void clearControls(){
        Email.setText("");
        Pass1.setText("");
        Pass2.setText("");
    }
}