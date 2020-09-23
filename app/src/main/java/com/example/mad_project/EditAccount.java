package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAccount extends AppCompatActivity {

    EditText fName, fEmail, fMobile, fAddress, fNic, fPassword, fConfirmPassword;
    Button Update, delete;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        db = new DatabaseHelper(this);
        fName = findViewById(R.id.name);
        fEmail = findViewById(R.id.email);
        fMobile = findViewById(R.id.mobile);
        fAddress = findViewById(R.id.address);
        fNic = findViewById(R.id.nic);
        fPassword = findViewById(R.id.password);
        Update = findViewById(R.id.Update);
        delete = findViewById(R.id.button7);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fName.getText().toString().equals("")) {
                    fName.setError("Enter the Name");

                } else if (fEmail.getText().toString().equals("")) {
                    fEmail.setError("Enter the Email Address");

                } else if (fNic.getText().toString().equals("")) {
                    fNic.setError("Enter the NIC Number");

                } else if (fMobile.getText().toString().equals("")) {
                    fMobile.setError("Enter the Mobile Number");

                } else if (fAddress.getText().toString().equals("")) {
                    fAddress.setError("Enter the Address");

                } else if (fPassword.getText().toString().equals("")) {
                    fPassword.setError("Enter the Password");


                } else if (fPassword.getText().toString().length() < 6) {
                    fPassword.setError("Password must be => 6 characters!");

                } else if (fMobile.getText().toString().length() < 10) {
                    fMobile.setError("Mobile Number must be => 10 characters!");

                } else {
                    db.update(fName.getText().toString(), fEmail.getText().toString(), fNic.getText().toString(), fMobile.getText().toString(), fAddress.getText().toString(), fPassword.getText().toString());

                    Toast.makeText(EditAccount.this, "Entry Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = fEmail.getText().toString();
                Boolean checkudeletedata = db.delete(Email);
                if (checkudeletedata == true) {
                    Toast.makeText(EditAccount.this, "Entry Deleted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(EditAccount.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




    /*public void onClick(View view) {
        Cursor res = db.get();
        if(res.getCount()==0){
            Toast.makeText(EditAccount.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Name :"+res.getString(0)+"\n");
            buffer.append("Contact :"+res.getString(1)+"\n");
            buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
        }
}


       /* btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("removed from database");
            }
        });

             
                    }
                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
   /* private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }*/
