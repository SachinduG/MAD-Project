package com.example.mad_project.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.mad_project.model.Parks;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME="parkdata.db";
    private static final int DB_VER=1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    //GET ALL PARKS
    public List<Parks> getParks()
    {
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

        String[] sqlSelect={"ID","Email","Town","Address","Mobile","Description"};
        String tableName="Parks";

        qb.setTables(tableName);
        Cursor cursor=qb.query(db,sqlSelect,null,null,null,null,null);
        List<Parks> result=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Parks parks=new Parks();
                parks.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                parks.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                parks.setTown(cursor.getString(cursor.getColumnIndex("Town")));
                parks.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
                parks.setMobile(cursor.getString(cursor.getColumnIndex("Mobile")));
                parks.setDescription(cursor.getString(cursor.getColumnIndex("Description")));

                result.add(parks);
            }while (cursor.moveToNext());
        }
        return result;
    }

    //function get all parks
    public List<String> getTowns()
    {
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

        String[] sqlSelect={"Town"};
        String tableName="Parks";

        qb.setTables(tableName);
        Cursor cursor=qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{

                result.add(cursor.getString(cursor.getColumnIndex("Town")));
            }while (cursor.moveToNext());
        }
        return result;
    }


    //function to get park by town
    public List<Parks>getParkbyTown(String town)
    {
        SQLiteDatabase db=getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

        String[] sqlSelect={"ID","Email","Town","Address","Mobile","Description"};
        String tableName="Parks";

        qb.setTables(tableName);
        Cursor cursor=qb.query(db,sqlSelect,"Town LIKE ?",new String[]{"%"+town+"%"},null,null,null);
        List<Parks> result=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                Parks parks=new Parks();
                parks.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                parks.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                parks.setTown(cursor.getString(cursor.getColumnIndex("Town")));
                parks.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
                parks.setMobile(cursor.getString(cursor.getColumnIndex("Mobile")));
                parks.setDescription(cursor.getString(cursor.getColumnIndex("Description")));

                result.add(parks);
            }while (cursor.moveToNext());
        }
        return result;

    }
}
