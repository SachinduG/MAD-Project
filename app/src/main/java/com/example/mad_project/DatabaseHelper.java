package com.example.mad_project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context){
        super(context, "Customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table User(email text primary key, name text, mobile text, nic text, address text, password text)");
        db.execSQL("Create table Feedback(email text primary key, name text, message text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Feedback");
    }

    public Boolean insert(String name, String email, String mobile, String nic, String address, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);
        contentValues.put("nic",nic);
        contentValues.put("address",address);
        contentValues.put("password",password);
        long result = db.insert("User", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean update(String name, String email, String mobile, String nic, String address, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);
        contentValues.put("nic",nic);
        contentValues.put("address",address);
        contentValues.put("password",password);
        Cursor cursor = db.rawQuery("Select * from User where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.update("User", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

    public Boolean delete (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = db.delete("User", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor get ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User", null);
        return cursor;

    }



    public Boolean insert(String name, String email, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("message",message);
        long result = db.insert("Feedback", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email=?",new String[]{email});
        if(cursor.getCount() > 0)
            return false;
        else
            return true;
    }


    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email=? and password=?",new String[] {email,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
