package com.example.appenglish;

import static com.example.appenglish.Model.EngLishAppDatabaseAdapter.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.appenglish.Model.EngLishAppDatabaseAdapter;

public class DatabaseAppHelper extends SQLiteOpenHelper {
    public DatabaseAppHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL(EngLishAppDatabaseAdapter.DATABASE_CREATE);
        }catch(Exception er){
            Log.e("Error","exceptioin");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        _db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        _db.execSQL("DROP TABLE IF EXISTS " + "SEMESTER1");
        // Tạo một database mới.
        onCreate(_db);
    }
}
