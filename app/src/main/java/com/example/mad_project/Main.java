package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    TextView Email;
    Button btnLogout,account;
    SessionManager sessionManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main1);

            btnLogout = findViewById(R.id.btnLogout);
            Email = findViewById(R.id.tvEmailMain);
            sessionManager = new SessionManager(getApplicationContext());

            String EmailAddress = sessionManager.getEmail();
            Email.setText(EmailAddress);

            btnLogout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Log out");
                    builder.setMessage("Are you sure to log out?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sessionManager.setLogin(false);
                            sessionManager.setEmail("");


                            startActivity(new Intent(getApplicationContext(),SignIn.class));
                            finish();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                account=findViewById(R.id.button8);
                account.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),AccountControl.class));
                        finish();
                    }
                });
                }
            });
        }
}