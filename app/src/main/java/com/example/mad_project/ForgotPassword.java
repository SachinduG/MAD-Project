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
    EditText Email;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Next = findViewById(R.id.btnConfirm);
        Email = findViewById(R.id.forgotEmail);
        db = new DatabaseHelper(this);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailAddress = Email.getText().toString().trim();
                String EmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                Boolean CheckUser = db.checkUser(Email.getText().toString());

                if (CheckUser.equals(true)) {
                    Toast.makeText(getApplicationContext(), "Validate Email Address", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ResetPassword.class);
                    i.putExtra("EMAIL", EmailAddress);
                    startActivity(i);
                    clearControls();

                }else if(EmailAddress.matches(EmailPattern)){
                        Email.setError("Email Address doesn't exist");

                } else{
                    Email.setError("Enter your Email Address");
                }
            }
        });
    }
    private void clearControls(){
        Email.setText("");
    }
}