package com.example.mad_project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Cursor cursor;

    public DatabaseHelper(Context context) {
        super(context, "APS.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table User(email text primary key, name text, mobile text, nic text, address text, password text)");
        db.execSQL("Create table Feedback(email text primary key, name text, message text)");
        db.execSQL("Create table Park(email text primary key, town text, address text, mobile text, description text)");
        //db.execSQL("Create table Addparking(email text primary key,town text,address text,description text,image blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Feedback");
        db.execSQL("drop table if exists Park");

        //db.execSQL("drop table if exists Addparking");
    }

    public Boolean insert(String name, String email, String mobile, String nic, String address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("mobile", mobile);
        contentValues.put("nic", nic);
        contentValues.put("address", address);
        contentValues.put("password", password);
        long result = db.insert("User", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean parkinsert(String email, String town, String address, String mobile, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("town", town);
        contentValues.put("address", address);
        contentValues.put("mobile", mobile);
        contentValues.put("description", description);
        long result = db.insert("Park", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }





    public void addEntry(String email, String town, String address, String description,String mobile)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("town", town);
        cv.put("mobile",mobile);
        cv.put("address", address);
        cv.put("description", description);
        db.insert("Addparking", null, cv);
    }

    public void update(String name, String email, String mobile, String nic, String address, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put("email", email);
        contentValues.put("name", name);

        contentValues.put("mobile", mobile);
        contentValues.put("nic", nic);
        contentValues.put("address", address);
        contentValues.put("password", password);

        db.update("User", contentValues, "email = ?", new String[]{email});
        db.close();
    }


    public void delete(String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = db.delete("User", "email=?", new String[]{email});
        }

    }

    public void parkdelete(String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = db.delete("Park", "email=?", new String[]{email});
        }

    }

    public Cursor get() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from User";
        String lastrecord = " SELECT * FROM  + User + WHERE email = ?";
        Cursor res = db.rawQuery(query, null);
        return res;

    }


    

    /*public CustomerProfile findCustomer(String email, SQLiteDatabase db) {

        boolean customerExist = false;
        CustomerProfile customerProfile = null;
        String fName;
        String fEmail;
        String fMobile;
        String fAddress;
        String fNic;
        String fPassword;

        String[] projection = {
                DBContract.CustomerProfile.USER_EMAIL,
                DBContract.CustomerProfile.USER_NAME,
                DBContract.CustomerProfile.USER_MOBILE,
                DBContract.CustomerProfile.USER_NIC,
                DBContract.CustomerProfile.USER_ADDRESS,
                DBContract.CustomerProfile.USER_PASSWORD};

        Cursor cursor = null;
        try {
             cursor = db.query(
                    DBContract.CustomerProfile.USER_TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DBContract.CustomerProfile.USER_EMAIL + "=?",                                // The columns for the WHERE clause
                    new String[]{email},                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );
        } catch (Exception e) {
            e.printStackTrace();
//
            return null;

        }
        if (cursor.moveToFirst()) {

            fName = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CustomerProfile.USER_NAME));
            fEmail = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CustomerProfile.USER_EMAIL));
            fMobile = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CustomerProfile.USER_MOBILE));
            fAddress = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CustomerProfile.USER_ADDRESS));
            fNic = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CustomerProfile.USER_NIC));
            fPassword = cursor.getString(cursor.getColumnIndexOrThrow(DBContract.CustomerProfile.USER_PASSWORD));


            customerProfile = new CustomerProfile(fName, fEmail, fMobile, fAddress, fNic, fPassword);
        }
        return customerProfile;

    }*/


    public Cursor getname() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select name  from User", null);
        return cursor;

    }

    public Boolean insert(String name, String email, String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("message", message);
        long result = db.insert("Feedback", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean chkemail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email=?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public void updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);

        db.update("User", values, "email = ?", new String[]{email});

        db.close();
    }

    public Boolean checkUser(String email) {
        String[] columns = {
                "email"
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = "email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query("User",
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }



    public Cursor getdata(String mobile) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User where mobile=?", new String[]{mobile});
        return cursor;


    }

}






