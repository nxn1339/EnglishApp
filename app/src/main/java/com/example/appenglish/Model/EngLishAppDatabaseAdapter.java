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
    public static Boolean isCheckCreateUser = true;
    public static final String DATABASE_NAME = "dbEngLishApp.db";
    public static final  String TABLE_USER = "User";

    public static final String TABLE_TOPIC ="Topic";

    public static final String TABLE_USER_TOPIC ="UserTopic";
    public static final int DATABASE_VERSION = 1;
    // Câu lệnh SQL tạo mới cơ sở dữ liệu.
    public static final String DATABASE_CREATE_USER = "create table "+TABLE_USER+"(\n" +
            "\t\"id_user\"\tINTEGER NOT NULL,\n" +
            "\t\"user_name\"\ttext NOT NULL UNIQUE,\n" +
            "\t\"password\"\ttext NOT NULL,\n" +
            "\t\"lv\"\ttext NOT NULL,\n" +
            "\t\"role\"\ttext NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_user\" AUTOINCREMENT)\n" +
            "); ";
    public static final String DATABASE_CREATE_TOPIC = "create table "+TABLE_TOPIC+"(\n" +
            "\t\"id_topic\"\tINTEGER NOT NULL,\n" +
            "\t\"title\"\tTEXT NOT NULL,\n" +
            "\t\"point\"\tINTEGER NOT NULL,\n" +
            "\t\"img\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"id_topic\" AUTOINCREMENT)\n" +
            ");";
    public static final String DATABASE_CREATE_USER_TOPIC = "create table "+TABLE_USER_TOPIC+"(\n" +
            "\t\"id_user_topic\"\tINTEGER NOT NULL,\n" +
            "\t\"id_user\"\tINTEGER,\n" +
            "\t\"id_topic\"\tINTEGER,\n" +
            "\tPRIMARY KEY(\"id_user_topic\" AUTOINCREMENT),\n" +
            "\tFOREIGN KEY(\"id_topic\") REFERENCES \"Topic\"(\"id_topic\"),\n" +
            "\tFOREIGN KEY(\"id_user\") REFERENCES \"USERS\"(\"id_user\")\n" +
            ");";

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
    //========================== Phương thức insert ================================
    //User
    public String insertUser(String user_name, String password, String lv,String role)
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
            long result=db.insert(TABLE_USER, null, newValues);
            //kiểm tra tạo tài khoản thành công không
            if((int)result >0){
                isCheckCreateUser = true;
            }
            else {
                isCheckCreateUser = false;
            }
            Log.i("Row Insert Result ", String.valueOf(result));
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }

    // Topic
    public String insertTopic(String title, int point, String img)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("title", title);
            newValues.put("point", point);
            newValues.put("img", img);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_TOPIC, null, newValues);
            //kiểm tra tạo tài khoản thành công không
            if((int)result >0){
                isCheckCreateUser = true;
            }
            else {
                isCheckCreateUser = false;
            }
            Log.i("Thêm tiêu đề:", String.valueOf(result));
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }

    // Topic
    public String insertUserTopic( int id_user, int id_topic){
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("id_user", id_user);
            newValues.put("id_topic", id_topic);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_TOPIC, null, newValues);
            //kiểm tra tạo tài khoản thành công không
            if((int)result >0){
                isCheckCreateUser = true;
            }
            else {
                isCheckCreateUser = false;
            }
            Log.i("Thêm Liên kết:", String.valueOf(result));
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }


    //==================== Phương thức lấy tất cả các hàng được lưu trong Table=========================
    //User
    public static ArrayList<User> getRowUser() throws JSONException {

        User.users.clear();
        User user;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_USER, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            user = new User();
            user.setID(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user"))));
            user.setUser_name(projCursor.getString(projCursor.getColumnIndexOrThrow("user_name")));
            user.setPassword(projCursor.getString(projCursor.getColumnIndexOrThrow("password")));
            user.setLv(projCursor.getString(projCursor.getColumnIndexOrThrow("lv")));
            user.setRole(projCursor.getString(projCursor.getColumnIndexOrThrow("role")));
            User.users.add(user);
        }
        projCursor.close();
        return User.users;
    }
    //Topic
    public static ArrayList<Topic> getRowTopic() throws JSONException {

        Topic.topics.clear();
        Topic topic;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_TOPIC, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            topic = new Topic();
            topic.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            topic.setTitle(projCursor.getString(projCursor.getColumnIndexOrThrow("title")));
            topic.setPoint(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("point"))));
            topic.setImg(projCursor.getString(projCursor.getColumnIndexOrThrow("img")));
            Topic.topics.add(topic);
        }
        projCursor.close();
        return Topic.topics;
    }

    //User Topic
    public static ArrayList<UserTopic> getRowUserTopic() throws JSONException {
        UserTopic.userTopics.clear();
        UserTopic userTopic;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_USER_TOPIC, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            userTopic = new UserTopic();
            userTopic.setId_user_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user_topic"))));
            userTopic.setId_user(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user"))));
            userTopic.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));

            UserTopic.userTopics.add(userTopic);
        }
        projCursor.close();
        return UserTopic.userTopics;
    }
    //=========================== Phương thức đếm tổng số bản ghi trong Table==============================
    public int getRowCountUser()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_USER, null, null, null, null, null, null);
        db.close();
        return cursor.getCount();
    }
    //Topic
    public int getRowCountTopic()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_TOPIC, null, null, null, null, null, null);
        db.close();
        return cursor.getCount();
    }
    //User Topic
    public int getRowCountUserTopic()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_USER_TOPIC, null, null, null, null, null, null);
        db.close();
        return cursor.getCount();
    }

    // Phương thức xoá tất cả các bản ghi trong bảng Table
    public void truncateTable()
    {
        db=dbHelper.getReadableDatabase();
        db.delete(TABLE_USER, "1", null);
        db.delete(TABLE_TOPIC, "1", null);
        db.delete(TABLE_USER_TOPIC, "1", null);
        db.close();
        User.users.clear();
        Topic.topics.clear();
        UserTopic.userTopics.clear();
        Toast.makeText(context.getApplicationContext(),"Xóa tất cả thành công!",Toast.LENGTH_LONG).show();
    }
}
