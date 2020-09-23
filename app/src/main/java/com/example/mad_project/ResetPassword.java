package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {
    Button confirm;
    EditText pass1, pass2;
    DatabaseHelper db;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        db = new DatabaseHelper(this);
        confirm = findViewById(R.id.btnConfirm);
        pass1 = findViewById(R.id.resetpassword1);
        pass2 = findViewById(R.id.resetpassword2);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Password1 = pass1.getText().toString().trim();
                String Password2 = pass2.getText().toString().trim();

               Intent i = getIntent();
                email = i.getStringExtra("EMAIL");

                if(Password1.isEmpty()){
                    pass1.setError("Enter the Password");

                }else if(Password2.isEmpty()){
                    pass2.setError("Enter the Confirm Password");

                }else if(!Password1.contentEquals(Password2)){
                    pass1.setError("Passwords do not match");
                }else{
                    db.updatePassword(email, Password1);

                    Toast.makeText(getApplicationContext(), "Password reset successfully", Toast.LENGTH_SHORT).show();
                    emptyInputEditText();

                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(intent);
                    finish();
                }
            }
            private void emptyInputEditText()
            {
                pass1.setText("");
                pass2.setText("");
            }
        });
    }
}