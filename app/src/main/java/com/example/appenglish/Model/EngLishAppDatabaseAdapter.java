package com.example.appenglish.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.appenglish.DatabaseAppHelper;
import com.example.appenglish.Utils.Utils;

import org.json.JSONException;

import java.util.ArrayList;

public class EngLishAppDatabaseAdapter {
    public static  ArrayList<User> users=new ArrayList<>();
    public static final String DATABASE_NAME = "dbEngLishApp.db";
    public static final  String TABLE_NAME = "USERS";
    public static final int DATABASE_VERSION = 1;
    // Câu lệnh SQL tạo mới cơ sở dữ liệu.
    public static final String DATABASE_CREATE = "create table "+TABLE_NAME+"(user_name  text primary key,password  text,lv text,role text); ";
    private static final String TAG = "EngLishAppDatabaseAdapter";

    // Khai báo biên db kiểm SQLiteDatabase để thực thi các phương thức với cơ sở dữ liệu
    public static SQLiteDatabase db;
    // Khai báo đối tượng kiểu Context của ứng dụng sử dụng cơ sở dữ liệu này.
    private final Context context;
    // Database open/upgrade helper
    private static DatabaseAppHelper dbHelper;

    public EngLishAppDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DatabaseAppHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Phương thức mở Database
    public  EngLishAppDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Phương thức đóng Database
    public void close()
    {
        db.close();
    }
    // Phương thức trả về instance của Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    // Phương thức insert bản ghi vào Table
    public String insertEntry(String user_name, String password, String lv,String role)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("user_name", user_name);
            newValues.put("password", password);
            newValues.put("lv", lv);
            newValues.put("role", role);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            Utils.showToast(this.context.getApplicationContext(), "User Info Saved! Total Row Count is "+getRowCount());
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }

    // Phương thức lấy tất cả các hàng được lưu trong Table
    public static ArrayList<User> getRows() throws JSONException {

        users.clear();
        User user;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            user = new User();
            user.setUser_name(projCursor.getString(projCursor.getColumnIndexOrThrow("user_name")));
            user.setPassword(projCursor.getString(projCursor.getColumnIndexOrThrow("password")));
            user.setLv(projCursor.getString(projCursor.getColumnIndexOrThrow("lv")));
            user.setRole(projCursor.getString(projCursor.getColumnIndexOrThrow("role")));
            users.add(user);
        }
        projCursor.close();
        return users;
}
    // Phương thức đếm tổng số bản ghi trong Table
    public int getRowCount()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
        Toast.makeText(this.context.getApplicationContext(),"Row Count is "+cursor.getCount(),Toast.LENGTH_LONG).show();
        db.close();
        return cursor.getCount();
    }
    // Phương thức xoá tất cả các bản ghi trong bảng Table
    public void truncateTable()
    {
        db=dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "1", null);
        db.close();
        Toast.makeText(context.getApplicationContext(),"Table Data Truncated!",Toast.LENGTH_LONG).show();
    }
}
