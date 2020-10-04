package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DBHandler;

public class BookuserList extends AppCompatActivity {


    ListView userlist;
    ArrayAdapter adapter;
    ArrayList datalist;

    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookuser_list);




        userlist = findViewById(R.id.listBook1);
        db = new DBHandler(getApplicationContext());
        datalist = db.readAllInf1();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datalist);

        userlist.setAdapter(adapter);

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = userlist.getItemAtPosition(i).toString();
                Toast.makeText(BookuserList.this, "user"+text, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),SearchUsersBooking.class);
                    startActivity(intent);

            }
        });
    }
}