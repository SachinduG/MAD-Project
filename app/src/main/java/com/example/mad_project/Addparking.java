package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.ByteArrayOutputStream;


public class Addparking extends AppCompatActivity {


    EditText addemail,addtown,addaddress,adddescription;
    TextView rateperh;
    ImageView imageviewadd;
    Button choosebtn,addbtn;
    DatabaseHelper db;
    public static DatabaseHelper databaseHelper;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparking);

        db = new DatabaseHelper(this);
        addemail = findViewById(R.id.emid);
        addtown = (EditText) findViewById(R.id.townid);
        addaddress = (EditText) findViewById(R.id.addid);
        adddescription = (EditText) findViewById(R.id.adddis);
        choosebtn=(Button)findViewById(R.id.btnChoose);
        addbtn=(Button)findViewById(R.id.butnaddpark);
        rateperh=(TextView)findViewById(R.id.txtrateid);
        imageviewadd=(ImageView)findViewById(R.id.imageViewaddid);

      /*  choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        Addparking.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String Email = addemail.getText().toString().trim();
            String Town = addtown.getText().toString().trim();
            //String Address = addaddress.getText().toString().trim();
            //String Description = adddescription.getText().toString().trim();
            //String Image = imageviewadd.getText().toString().trim();


        }
*/    }
}



