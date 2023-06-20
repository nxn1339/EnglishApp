package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;
    private Context context;
    public static User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full viên màn hình
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        //tạo acc admin
        if(User.users.size()==0){
            engLishAppDatabaseAdapter.insertUser("adm","1","","0");
        }
        try {
            //lấy tải khoản mật khẩu đã lưu
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String userName = sharedPreferences.getString("USERNAME", "");
            String password = sharedPreferences.getString("PASSWORD", "");

            if(checkLogin(userName,password)==true){
                if(user.getRole().trim().equals("1")){
                    //tạo accivity home
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    Utils.showToast(getBaseContext(), "Đăng nhập thành công !");
                    //đi đến activity home
                    startActivity(intent);
                }
                else if(user.getRole().trim().equals("0")){
                    //tạo accivity admin home
                    Intent intent = new Intent(LoginActivity.this, AdminHome.class);
                    Utils.showToast(getBaseContext(), "Đăng nhập thành công !");
                    //đi đến activity home
                    startActivity(intent);
                }
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
                    Log.i("Lỗi ở đăng nhập","AAA");
                    e.printStackTrace();
                }
//                engLishAppDatabaseAdapter.truncateTable();
                if(checkLogin(txtUserName.getText().toString().trim(),txtPassword.getText().toString().trim())==true){
                    if(user.getRole().trim().equals("1")){
                        //tạo accivity home
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Utils.showToast(getBaseContext(), "Đăng nhập thành công !");
                        //đi đến activity home
                        startActivity(intent);
                    }
                    else  if(user.getRole().trim().equals("0")){
                        //tạo accivity admin home
                        Intent intent = new Intent(LoginActivity.this, AdminHome.class);
                        Utils.showToast(getBaseContext(), "Đăng nhập thành công !");
                        //đi đến activity home
                        startActivity(intent);
                    }

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
        //Lấy dữ liệu
        try {
            User.users = engLishAppDatabaseAdapter.getRowUser();
        } catch (JSONException e) {
            Log.i("Lỗi ở đăng nhập","Sửa đi");
            e.printStackTrace();
        }
        for (int i=0;i<User.users.toArray().length;i++){
            if(userName.equals(User.users.get(i).getUser_name()) && password.equals(User.users.get(i).getPassword())){
                //lưu user cho lần sau
                saveUser(userName,password);
                user.setID(User.users.get(i).getID());
                user.setUser_name(User.users.get(i).getUser_name());
                user.setFull_name(User.users.get(i).getFull_name());
                user.setImg_avatar(User.users.get(i).getImg_avatar());
                user.setRole(User.users.get(i).getRole());
                return true;
            }
        }
        return false;
    }
    private void saveUser(String userName, String password){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor user = sharedPreferences.edit();
        user.putString("USERNAME", userName); // Lưu một chuỗi với khóa "key"
        user.putString("PASSWORD", password); // Lưu một chuỗi với khóa "key"
        user.apply(); // Áp dụng các thay đổi vào Shared Preferences
    }

}