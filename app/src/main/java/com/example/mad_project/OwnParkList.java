package com.example.mad_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mad_project.model.Park;

import java.util.ArrayList;
import java.util.List;

public class OwnParkList extends AppCompatActivity {
    Button addpark;
    ListView listview;
    Context context;
    DbHandler dbHandler;
    List<Park> parks;
    TextView count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_park_list);
        context = this;

        dbHandler = new DbHandler(context);
        addpark  = findViewById(R.id.addparking);
        listview = findViewById(R.id.parklist);
        count = findViewById(R.id.addcount);
        parks  = new ArrayList<>();

        parks = dbHandler.getallparks();
        ParkAdapter adapter  = new ParkAdapter(context,R.layout.singlepark,parks);

        listview.setAdapter(adapter);

        int countpark = dbHandler.countpark();
        count.setText("You Added"  +countpark+  "Parks");


        addpark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,Addparking.class));
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final Park park = parks.get(position);
                builder.setTitle(park.getEmail());
                builder.setMessage(park.getTown());
                builder.setMessage(park.getAddress());
                builder.setMessage(park.getMobile());
                builder.setMessage(park.getDescription());

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent  = new Intent(context,EditPArking.class);
                        intent.putExtra("id",String.valueOf(park.getId()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.parkdelete(park.getId());
                        startActivity(new Intent(context,OwnParkList.class));
                    }
                });
                builder.show();
            }
        });

    }
}