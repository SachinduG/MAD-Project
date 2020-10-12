package com.example.mad_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mad_project.model.Park;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "park";
    private static final String TABLE_NAME = "park";
    // Column names
    private static final String ID = "id";
    private static final String EMAIL= "email";
    private static final String TOWN = "town";
    private static final String ADDRESS = "address";
    private static final String MOBILE = "mobile";
    private static final String DESCRIPTION = "description";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE_QUERY = " CREATE TABLE "+TABLE_NAME+" "+
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EMAIL+" TEXT,"
                +TOWN + " TEXT,"
                +ADDRESS+ " TEXT,"
                +MOBILE + " TEXT,"
                +DESCRIPTION+" TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(db);
    }
    public void addpark(Park park){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL,park.getEmail());
        contentValues.put(TOWN,park.getTown());
        contentValues.put(ADDRESS,park.getAddress());
        contentValues.put(MOBILE,park.getMobile());
        contentValues.put(DESCRIPTION,park.getDescription());


        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
    }
    public List<Park> getallparks(){
        List<Park> parks = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String qurey = "SELECT * FROM " +TABLE_NAME;

        Cursor cursor = db.rawQuery(qurey,null);

        if(cursor.moveToFirst()){
            do{
                Park park = new Park();
                park.setId(cursor.getInt(0));
                park.setEmail(cursor.getString(1));
                park.setTown(cursor.getString(2));
                park.setAddress(cursor.getString(3));
                park.setMobile(cursor.getString(4));
                park.setDescription(cursor.getString(5));

                parks.add(park);

            }while (cursor.moveToNext());

        }
        return parks;
    }

    public Park getsinglepark(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,EMAIL,TOWN,ADDRESS,MOBILE,DESCRIPTION}, ID + "= ?",new String[]{String.valueOf(id)},null,null,null
        );
        Park park;
        if(cursor != null){
            cursor.moveToFirst();
            park = new Park(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            return park;
        }
        return null;

    }

    public int parkupdate(Park park){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(EMAIL,park.getEmail());
        contentValues.put(TOWN,park.getTown());
        contentValues.put(ADDRESS,park.getAddress());
        contentValues.put(MOBILE,park.getMobile());
        contentValues.put(DESCRIPTION,park.getDescription());

        int status = db.update(TABLE_NAME,contentValues, ID + " =? ",new String[]{String.valueOf(park.getId())});
        db.close();
        return status;
    }

    public void parkdelete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

public int countpark(){
    SQLiteDatabase db = getReadableDatabase();
    String query = "SELECT * FROM "+TABLE_NAME;
    Cursor cursor = db.rawQuery(query,null);
    return  cursor.getCount();


}

}
