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
    TextView SignUp;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new DatabaseHelper(this);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        SignIn = findViewById(R.id.btnSignIn);
        SignUp = findViewById(R.id.tvSignUp);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean Chkemailpass = db.emailpassword(etEmail.getText().toString(), etPassword.getText().toString());
                if(Chkemailpass==true){
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);
                }else if(etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "All Fields must be filled!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Email Address or Password",Toast.LENGTH_SHORT).show();
                }

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }

        });
    }
}