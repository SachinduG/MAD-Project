package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    EditText name, email, message;
    Button btnView, btnSend;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        db = new DatabaseHelper(this);
        name = findViewById(R.id.FD_name);
        email = findViewById(R.id.FD_email);
        message = findViewById(R.id.FD_message);
        btnSend = findViewById(R.id.btnFD);
        btnView = findViewById(R.id.btnViewFeedback);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String FullName = name.getText().toString().trim();
                String Email = email.getText().toString().trim();
                boolean isValid = Utils.checkEmailForValidity(email.getText().toString());
                String Message = message.getText().toString().trim();

                if (FullName.isEmpty()){
                    name.setError("Fill your Name");

                }else if(Email.isEmpty()) {
                    email.setError("Fill your Email Address");

                }else if(!isValid){
                    email.setError("Enter valid Email Address");

                }else if(Message.isEmpty()){
                    message.setError("Fill your Message");

                }else {
                    Boolean insert = db.insert(FullName, Email, Message);

                    if (insert.equals(true)) {
                        Toast.makeText(getApplicationContext(), "Sent Feedback Successfully", Toast.LENGTH_SHORT).show();
                        btnSend.setEnabled(true);
                        btnView.setEnabled(true);

                    }else{
                        Toast.makeText(getApplicationContext(), "Cannot Sent Feedback", Toast.LENGTH_SHORT).show();
                    }
                }

                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new AlertDialog.Builder(Feedback.this)
                                .setTitle("Your Feedback:")
                                .setMessage("Full Name :- " + name.getText().toString() + "\n\nEmail Address :- " + email.getText().toString() + "\n\nMessage :- " + message.getText().toString())
                                .show();
                        clearControls();
                    }
                });
            }
        });
    }

    private void clearControls(){
        name.setText("");
        email.setText("");
        message.setText("");

    }
}