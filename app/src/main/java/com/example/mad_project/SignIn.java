package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button SignIn;
    TextView SignUp, Password;
    DatabaseHelper db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new DatabaseHelper(this);
        etEmail = findViewById(R.id.SigninEmail);
        etPassword = findViewById(R.id.SigninPassword);
        SignIn = findViewById(R.id.btnSignIn);
        SignUp = findViewById(R.id.tvSignUp);
        Password = findViewById(R.id.tvPassword);

        sessionManager = new SessionManager(getApplicationContext());

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = etEmail.getText().toString().trim();
                boolean isValid = Utils.checkEmailForValidity(etEmail.getText().toString());
                String Password = etPassword.getText().toString().trim();

                Boolean CheckUser = db.emailpassword(Email, Password);

                if(CheckUser.equals(true)){
                    sessionManager.setLogin(true);
                    sessionManager.setEmail(Email);
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                    clearControls();
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);


                }else if(Email.isEmpty()){
                    etEmail.setError("Enter your Email Address");

                }else if(Password.isEmpty()) {
                    etPassword.setError("Enter your Password");

                }else if(!isValid){
                    etEmail.setError("Invalid Email Address!");

                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Email Address or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(sessionManager.getLogin()){
            startActivity(new Intent(getApplicationContext(),Main.class));
        }

        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }

        });

        Password.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
            }
        });

    }
    private void clearControls(){
        etEmail.setText("");
        etPassword.setText("");
    }
}