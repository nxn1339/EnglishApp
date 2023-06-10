package com.example.appenglish;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.appenglish.Model.Database;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;

public class DatabaseAppHelper extends SQLiteOpenHelper {
    public DatabaseAppHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(Database.DATABASE_CREATE_USER);
            _db.execSQL(Database.DATABASE_CREATE_TOPIC);
            _db.execSQL(Database.DATABASE_CREATE_USER_TOPIC);
            _db.execSQL(Database.DATABASE_CREATE_QUESTION);
            _db.execSQL(Database.DATABASE_CREATE_ANSWER);
        }catch(Exception er){
            Log.e("Error","exceptioin");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        _db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_USER);
        _db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_TOPIC);
        _db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_USER_TOPIC);
        _db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_QUESTION);
        _db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_ANSWER);
        // Tạo một database mới.
        onCreate(_db);
    }
}
