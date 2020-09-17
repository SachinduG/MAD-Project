package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.os.Bundle;

public class AccountControl extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        OnClickButtonLister();
        OnClickButtonLister1();
        OnClickButtonLister2();
    }
    public void OnClickButtonLister(){
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
    public void OnClickButtonLister1(){
        button = (Button)findViewById(R.id.button3);
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
    public void OnClickButtonLister2(){
        button = (Button)findViewById(R.id.button4);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(AccountControl.this, Change_Password.class);
                        startActivity(intent);
                    }
                }
        );
    }
}