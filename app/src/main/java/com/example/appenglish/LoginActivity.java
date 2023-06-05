package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.Model.User;
import com.example.appenglish.Model.UserTopic;
import com.example.appenglish.Utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {
    private TextView tvResgister;
    private Button btnLogin;
    private TextInputEditText txtUserName;
    private TextInputEditText txtPassword;


    private
    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;
    private Context context;
    public static User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvResgister = findViewById(R.id.tvResgister);
        txtUserName = (TextInputEditText) findViewById(R.id.txtUserName);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        // create the instance of Databse
        engLishAppDatabaseAdapter =new EngLishAppDatabaseAdapter(getApplicationContext());
        //Lấy dữ liệu
        try {
            User.users = engLishAppDatabaseAdapter.getRowUser();
            Topic.topics =engLishAppDatabaseAdapter.getRowTopic();
            UserTopic.userTopics =engLishAppDatabaseAdapter.getRowUserTopic();
        } catch (JSONException e) {
            Log.i("Lỗi ở đăng nhập","Sửa đi");
            e.printStackTrace();
        }
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String userName = sharedPreferences.getString("USERNAME", "");
            String password = sharedPreferences.getString("PASSWORD", "");
            int id_user = sharedPreferences.getInt("ID_USER",1);
            String lv = sharedPreferences.getString("LV", "");
            user.setID(id_user);
            user.setUser_name("");
            user.setPassword("");
            user.setRole("");
            user.setLv(lv);

            if(checkLogin(userName,password)==true){
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                Utils.showToast(getBaseContext(), "Đăng nhập thành công !");
                //đi đến activity home
                startActivity(intent);
            }
        }
        catch (Exception e){


        }


        //bấm nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Lấy dữ liệu
                try {
                    User.users = engLishAppDatabaseAdapter.getRowUser();
                    Topic.topics =engLishAppDatabaseAdapter.getRowTopic();
                    UserTopic.userTopics =engLishAppDatabaseAdapter.getRowUserTopic();
                } catch (JSONException e) {
                    Log.i("Lỗi ở đăng nhập","Sửa đi");
                    e.printStackTrace();
                }
//                engLishAppDatabaseAdapter.truncateTable();
                if(checkLogin(txtUserName.getText().toString().trim(),txtPassword.getText().toString().trim())==true){
                    //tạo accivity home
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    Utils.showToast(getBaseContext(), "Đăng nhập thành công !");
                    //đi đến activity home
                    startActivity(intent);
                }
                else{
                    Utils.showToast(getBaseContext(), "Tài khoản mật khẩu không chính xác !");
                }

           }
        });

        //bấm nút đăng ký
        tvResgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tạo accivity đăng ký
                Intent i = new Intent(getApplicationContext(), RegisterScreen.class);
                //đi đến acctivity đăng ký
                startActivity(i);
//                engLishAppDatabaseAdapter.insertEntry("admin","1","0","1");
//                engLishAppDatabaseAdapter.insertEntry("ngat","1","0","1");
//                engLishAppDatabaseAdapter.insertEntry("dieu","1","0","1");
            }
        });

    }

    //kiểm tra đăng nhập
    private Boolean checkLogin(String userName,String password){
        for (int i=0;i<User.users.toArray().length;i++){
            if(userName.equals(User.users.get(i).getUser_name()) && password.equals(User.users.get(i).getPassword())){
                //lưu user cho lần sau
                saveUser(userName,password,User.users.get(i).getID(),User.users.get(i).getLv());
                return true;
            }
        }
        return false;
    }
    private  void saveUser(String userName,String password,int id_user,String lv){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor user = sharedPreferences.edit();
        user.putString("USERNAME", userName); // Lưu một chuỗi với khóa "key"
        user.putString("PASSWORD", password); // Lưu một chuỗi với khóa "key"
        user.putInt("ID_USER",id_user);
        user.putString("LV",lv);
        user.apply(); // Áp dụng các thay đổi vào Shared Preferences
    }

}