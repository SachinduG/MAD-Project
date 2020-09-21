package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_BOOk);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private static final String SQL_CREATE_ENTRIES_BOOk =
            "CREATE TABLE " + BOOKUser.User.TABLE_NAME + " (" +
                   BOOKUser.User._ID + " INTEGER PRIMARY KEY," +
                    BOOKUser.User.COLUMN_1 + " TEXT," +
                    BOOKUser.User.COLUMN_2 + " TEXT," +
                    BOOKUser.User.COLUMN_3 + " TEXT," +
                    BOOKUser.User.COLUMN_4 + " TEXT)";




    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BOOKUser.User.TABLE_NAME;




    public long Addinformation(String name,String email,String nic,String mobile){



        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BOOKUser.User.COLUMN_1, name);
        values.put(BOOKUser.User.COLUMN_2, email);
        values.put(BOOKUser.User.COLUMN_3, nic);
        values.put(BOOKUser.User.COLUMN_4, mobile);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(BOOKUser.User.TABLE_NAME, null, values);

        return  newRowId;
    }

}
