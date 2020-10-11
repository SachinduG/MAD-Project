package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountControl extends AppCompatActivity {
    Button button;
    DatabaseHelper db;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        OnClickButtonLister();
        OnClickButtonLister1();
        OnClickButtonLister2();
        OnClickButtonLister3();
        OnClickButtonLister4 ();
        sessionManager = new SessionManager(getApplicationContext());


    }

        public void OnClickButtonLister () {
            button = (Button) findViewById(R.id.button1);
            button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(AccountControl.this, EditAccount.class);
                            startActivity(intent);
                        }
                    }
            );
        }

        public void OnClickButtonLister1 () {
            button = (Button) findViewById(R.id.button3);
            button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(AccountControl.this, history.class);
                            startActivity(intent);
                        }
                    }
            );
        }

        public void OnClickButtonLister2 () {
            button = (Button) findViewById(R.id.button5);
            button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(AccountControl.this, Feedback.class);
                        startActivity(intent);
                    }
                }
            );
        }
    public void OnClickButtonLister4 () {
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(AccountControl.this, EditPArking.class);
                        startActivity(intent);
                    }
                }
        );
    }

        public void OnClickButtonLister3(){
            button = (Button) findViewById(R.id.button4);
            button.setOnClickListener(
                    new View.OnClickListener() {
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

                                    startActivity(new Intent(getApplicationContext(), SignIn.class));
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
                        }
                    }
            );
        }


}
