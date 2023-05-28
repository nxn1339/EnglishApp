package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.User;
import com.example.appenglish.Utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private TextView tvResgister;
    private Button btnLogin;
    private TextInputEditText txtUserName;
    private TextInputEditText txtPassword;
    private
    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;
    private String userName;
    private String password;
    ArrayList<User> users=new ArrayList<>();
    private Context context;
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
            users = engLishAppDatabaseAdapter.getRows();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //bấm nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                engLishAppDatabaseAdapter.insertEntry("a","1","0","1");

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
                Intent i = new Intent(getApplicationContext(), ResgisterScreen.class);
                //đi đến acctivity đăng ký
                startActivity(i);
            }
        });

    }

    //kiểm tra đăng nhập
    private Boolean checkLogin(String userName,String password){
        for (int i=0;i<users.toArray().length;i++){
            if(userName.equals(users.get(i).getUser_name()) && password.equals(users.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }
}