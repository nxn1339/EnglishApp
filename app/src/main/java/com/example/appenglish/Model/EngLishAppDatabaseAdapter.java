package com.example.appenglish.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.appenglish.DatabaseAppHelper;
import com.example.appenglish.LoginActivity;
import com.example.appenglish.Utils.Utils;
import com.example.appenglish.databinding.ActivityHomeBinding;

import org.json.JSONException;

import java.util.ArrayList;

public class EngLishAppDatabaseAdapter {
    public static Boolean isCheckCreateUser = true;


    // Khai báo biên db kiểm SQLiteDatabase để thực thi các phương thức với cơ sở dữ liệu
    public static SQLiteDatabase db;
    // Khai báo đối tượng kiểu Context của ứng dụng sử dụng cơ sở dữ liệu này.
    private final Context context;
    // Database open/upgrade helper
    private static DatabaseAppHelper dbHelper;

    public EngLishAppDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DatabaseAppHelper(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
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
    public String insertUser(String user_name, String password, String img_avatar,String role)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("user_name", user_name);
            newValues.put("password", password);
            newValues.put("img_avatar", img_avatar);
            newValues.put("role", role);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(Database.TABLE_USER, null, newValues);
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
    public String insertTopic(String title, String img)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("title", title);
            newValues.put("img", img);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(Database.TABLE_TOPIC, null, newValues);
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
    public String insertUserTopic( int id_user, int id_topic,int point){
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("id_user", id_user);
            newValues.put("id_topic", id_topic);
            newValues.put("point", point);

            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(Database.TABLE_USER_TOPIC, null, newValues);
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
    //Question
    public String insertQuestion(int id_topic,String question,int type){
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("id_topic", id_topic);
            newValues.put("question", question);
            newValues.put("type", type);
            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(Database.TABLE_QUESTION, null, newValues);
            //kiểm tra tạo tài khoản thành công không
            if((int)result >0){
                isCheckCreateUser = true;
            }
            else {
                isCheckCreateUser = false;
            }
            Log.i("Thêm Câu Hỏi:", String.valueOf(result));
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }

    //Answer
    public String insertAnswer(int id_question,int id_topic,String answer,int correct){
        try {
            ContentValues newValues = new ContentValues();
            // Gán dữ liệu cho mỗi cột.
            newValues.put("id_question", id_question);
            newValues.put("id_topic", id_topic);
            newValues.put("answer", answer);
            newValues.put("correct", correct);
            // Insert hàng dữ liệu vào table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(Database.TABLE_ANSWER, null, newValues);
            //kiểm tra tạo tài khoản thành công không
            if((int)result >0){
                isCheckCreateUser = true;
            }
            else {
                isCheckCreateUser = false;
            }
            Log.i("Thêm câu trả lời:", String.valueOf(result));
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
        Cursor projCursor = db.query(Database.TABLE_USER, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            user = new User();
            user.setID(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user"))));
            user.setUser_name(projCursor.getString(projCursor.getColumnIndexOrThrow("user_name")));
            user.setPassword(projCursor.getString(projCursor.getColumnIndexOrThrow("password")));
            user.setImg_avatar(projCursor.getString(projCursor.getColumnIndexOrThrow("img_avatar")));
            user.setFull_name(projCursor.getString(projCursor.getColumnIndexOrThrow("full_name")));
            user.setRole(projCursor.getString(projCursor.getColumnIndexOrThrow("role")));
            User.users.add(user);
        }
        projCursor.close();
        return User.users;
    }

    public static ArrayList<User> getRowUserProfile(int id_user) throws JSONException {

        User.users.clear();
        User user;
        db=dbHelper.getReadableDatabase();

        Cursor projCursor = db.rawQuery("SELECT * FROM '"+Database.TABLE_USER+"' WHERE id_user='"+id_user+"'",null);
        while (projCursor.moveToNext()) {

            user = new User();
            user.setID(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user"))));
            user.setUser_name(projCursor.getString(projCursor.getColumnIndexOrThrow("user_name")));
            user.setPassword(projCursor.getString(projCursor.getColumnIndexOrThrow("password")));
            user.setImg_avatar(projCursor.getString(projCursor.getColumnIndexOrThrow("img_avatar")));
            user.setFull_name(projCursor.getString(projCursor.getColumnIndexOrThrow("full_name")));
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
        Cursor projCursor = db.query(Database.TABLE_TOPIC, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            topic = new Topic();
            topic.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            topic.setTitle(projCursor.getString(projCursor.getColumnIndexOrThrow("title")));
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
        Cursor projCursor = db.rawQuery("SELECT * FROM '"+Database.TABLE_USER_TOPIC+"' WHERE id_user = '"+ LoginActivity.user.getID()+"'",null);
        while (projCursor.moveToNext()) {

            userTopic = new UserTopic();
            userTopic.setId_user_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user_topic"))));
            userTopic.setId_user(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_user"))));
            userTopic.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            userTopic.setPoint(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("point"))));

            UserTopic.userTopics.add(userTopic);
        }
        projCursor.close();
        return UserTopic.userTopics;
    }
    //Question của 1 Topic
    public static ArrayList<Question> getRowOneTopicQuestion(int id_topic) throws JSONException {
        Question.questions.clear();
        Question question;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.rawQuery("SELECT * FROM '"+Database.TABLE_QUESTION+"' WHERE id_topic = '"+ id_topic +"'",null);
        while (projCursor.moveToNext()) {

            question = new Question();
            question.setId_question(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_question"))));
            question.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            question.setQuestion(projCursor.getString(projCursor.getColumnIndexOrThrow("question")));
            question.setType(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("type"))));

            Question.questions.add(question);
        }
        projCursor.close();
        return Question.questions;
    }

    //Question của tất cả topic
    public static ArrayList<Question> getRowAllTopicQuestion() throws JSONException {
        Question.questions.clear();
        Question question;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(Database.TABLE_QUESTION,null,null,null,null,null,null);
        while (projCursor.moveToNext()) {

            question = new Question();
            question.setId_question(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_question"))));
            question.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            question.setQuestion(projCursor.getString(projCursor.getColumnIndexOrThrow("question")));
            question.setType(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("type"))));

            Question.questions.add(question);
        }
        projCursor.close();
        return Question.questions;
    }

    //Answer
    public static ArrayList<Answer> getRowAnswer(int id_question,int id_topic) throws JSONException {
        Answer.answers.clear();
        Answer answer;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.rawQuery("SELECT * FROM '"+Database.TABLE_ANSWER+"' WHERE id_question = '"+ id_question +"' AND id_topic = '"+ id_topic +"'",null);
        while (projCursor.moveToNext()) {

            answer = new Answer();
            answer.setId_answer(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_answer"))));
            answer.setId_question(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_question"))));
            answer.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            answer.setAnswer(projCursor.getString(projCursor.getColumnIndexOrThrow("answer")));
            answer.setCorrect(projCursor.getInt(projCursor.getColumnIndexOrThrow("correct")));
            Answer.answers.add(answer);
        }
        projCursor.close();
        return Answer.answers;
    }

    public static ArrayList<Answer> getRowAllAnswer() throws JSONException {
        Answer.answers.clear();
        Answer answer;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(Database.TABLE_ANSWER,null,null,null,null,null,null);
        while (projCursor.moveToNext()) {

            answer = new Answer();
            answer.setId_answer(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_answer"))));
            answer.setId_question(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_question"))));
            answer.setId_topic(Integer.parseInt(projCursor.getString(projCursor.getColumnIndexOrThrow("id_topic"))));
            answer.setAnswer(projCursor.getString(projCursor.getColumnIndexOrThrow("answer")));
            answer.setCorrect(projCursor.getInt(projCursor.getColumnIndexOrThrow("correct")));
            Answer.answers.add(answer);
        }
        projCursor.close();
        return Answer.answers;
    }



    //=========================== Phương thức đếm tổng số bản ghi trong Table==============================

    //Đếm điểm hoàn thành topic
    public static int getRowCountPoint(int id_user)
    {
        int point=0;
        try{
            db=dbHelper.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT * FROM '"+Database.TABLE_USER_TOPIC+"' WHERE id_user='"+id_user+"' AND point='10'",null);
           point = cursor.getCount();
            cursor.close();
            db.close();
        }
        catch (Exception e){
            Log.i("NNN",e.toString());
        }
        return point;
    }

    // Phương thức xoá tất cả các bản ghi trong bảng Table
    public void truncateTable()
    {
        db=dbHelper.getReadableDatabase();
        db.delete(Database.TABLE_USER, "1", null);
        db.delete(Database.TABLE_TOPIC, "1", null);
        db.delete(Database.TABLE_USER_TOPIC, "1", null);
        db.close();
        User.users.clear();
        Topic.topics.clear();
        UserTopic.userTopics.clear();
        Toast.makeText(context.getApplicationContext(),"Xóa tất cả thành công!",Toast.LENGTH_LONG).show();
    }

    //Update

    // Phương thức Update các bản ghi trong Table

    public  String updateUser(String user_name,String full_name,String password,String img_avatar)
    {
        try {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("user_name",user_name);
            updatedValues.put("full_name",full_name);
            updatedValues.put("password",password);
            updatedValues.put("img_avatar",img_avatar);
            updatedValues.put("role",1);
            String where="user_name=?";
            String [] whereArgs = new String[]{String.valueOf(user_name)};
            db=dbHelper.getReadableDatabase();
            db.update(Database.TABLE_USER,updatedValues, where,whereArgs);
            db.close();
            Utils.showToast(context,"Thay đổi thành công !");
        }
        catch (Exception e){
            Utils.showToast(this.context.getApplicationContext(),"Update không thành công !");
        }
        return "ok";

    }

    public  String updateUserMG(int id_user,String user_name,String password,String img_avatar,String full_name,String role)
    {
        try {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("user_name",user_name);
            updatedValues.put("full_name",full_name);
            updatedValues.put("password",password);
            updatedValues.put("img_avatar",img_avatar);
            updatedValues.put("role",role);
            String where="id_user=?";
            String [] whereArgs = new String[]{String.valueOf(id_user)};
            db=dbHelper.getReadableDatabase();
            db.update(Database.TABLE_USER,updatedValues, where,whereArgs);
            db.close();
            Utils.showToast(context,"Thay đổi thành công !");
        }
        catch (Exception e){
            Utils.showToast(this.context.getApplicationContext(),"Update không thành công !");
        }
        return "ok";

    }
    public  String updatePoint(int id_user,int id_topic,int point)
    {
        try {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("id_user",id_user);
            updatedValues.put("id_topic",id_topic);
            updatedValues.put("point",point);
            String where="id_user=? AND id_topic=?";
            String [] whereArgs = new String[]{String.valueOf(id_user),String.valueOf(id_topic)};
            db=dbHelper.getReadableDatabase();
            db.update(Database.TABLE_USER_TOPIC,updatedValues, where,whereArgs);
            db.close();
        }
        catch (Exception e){
            Utils.showToast(this.context.getApplicationContext(),"Update không thành công !");
        }
        return "ok";

    }

    public  String updateTopic(int id_topic,String title,String img)
    {
        try {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("title",title);
            updatedValues.put("img",img);
            String where="id_topic=?";
            String [] whereArgs = new String[]{String.valueOf(id_topic)};
            db=dbHelper.getReadableDatabase();
            db.update(Database.TABLE_TOPIC,updatedValues, where,whereArgs);
            db.close();
            Utils.showToast(this.context.getApplicationContext(),"Update thành công !");
        }
        catch (Exception e){
            Utils.showToast(this.context.getApplicationContext(),"Update không thành công !");
        }
        return "ok";

    }
    public  String updateQuest(int id_question,int id_topic,String question,int type)
    {
        try {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("id_topic",id_topic);
            updatedValues.put("question",question);
            updatedValues.put("type",type);
            String where="id_question=?";
            String [] whereArgs = new String[]{String.valueOf(id_question)};
            db=dbHelper.getReadableDatabase();
            db.update(Database.TABLE_QUESTION,updatedValues, where,whereArgs);
            db.close();
            Utils.showToast(this.context.getApplicationContext(),"Update thành công !");
        }
        catch (Exception e){
            Utils.showToast(this.context.getApplicationContext(),"Update không thành công !");
        }
        return "ok";

    }

    public  String updateAnswer(int id_answer,int id_question,int id_topic,String answer,int correct)
    {
        try {
            ContentValues updatedValues = new ContentValues();
            updatedValues.put("id_question",id_question);
            updatedValues.put("id_topic",id_topic);
            updatedValues.put("answer",answer);
            updatedValues.put("correct",correct);
            String where="id_answer=?";
            String [] whereArgs = new String[]{String.valueOf(id_answer)};
            db=dbHelper.getReadableDatabase();
            db.update(Database.TABLE_ANSWER,updatedValues, where,whereArgs);
            db.close();
            Utils.showToast(this.context.getApplicationContext(),"Update thành công !");
        }
        catch (Exception e){
            Utils.showToast(this.context.getApplicationContext(),"Update không thành công !");
        }
        return "ok";

    }

    public int deleteTopic(int ID)
    {
        String where="id_topic=?";
        int numberOFEntriesDeleted= db.delete(Database.TABLE_TOPIC, where, new String[]{String.valueOf(ID)}) ;
        Toast.makeText(this.context.getApplicationContext(),"Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }
    public int deleteUserTopic(int ID)
    {
        String where="id_topic=?";
        int numberOFEntriesDeleted= db.delete(Database.TABLE_USER_TOPIC, where, new String[]{String.valueOf(ID)}) ;
        Toast.makeText(this.context.getApplicationContext(),"Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }

    public int deleteQuestion(int ID)
    {
        String where="id_question=?";
        int numberOFEntriesDeleted= db.delete(Database.TABLE_QUESTION, where, new String[]{String.valueOf(ID)}) ;
        Toast.makeText(this.context.getApplicationContext(),"Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }

    public int deleteAnswer(int ID)
    {
        String where="id_answer=?";
        int numberOFEntriesDeleted= db.delete(Database.TABLE_ANSWER, where, new String[]{String.valueOf(ID)}) ;
        Toast.makeText(this.context.getApplicationContext(),"Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }
    public int deleteUser(int ID)
    {
        String where="id_user=?";
        int numberOFEntriesDeleted= db.delete(Database.TABLE_USER, where, new String[]{String.valueOf(ID)}) ;
        Toast.makeText(this.context.getApplicationContext(),"Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }
    public int deleteUserTopicMG(int ID)
    {
        String where="id_user=?";
        int numberOFEntriesDeleted= db.delete(Database.TABLE_USER_TOPIC, where, new String[]{String.valueOf(ID)}) ;
        Toast.makeText(this.context.getApplicationContext(),"Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_SHORT).show();
        return numberOFEntriesDeleted;
    }
}
