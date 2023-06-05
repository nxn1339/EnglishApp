package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.Model.User;
import com.example.appenglish.Model.UserTopic;
import com.example.appenglish.Utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

public class RegisterScreen extends AppCompatActivity {
    private TextInputEditText txtUserName;
    private TextInputEditText txtPassword;
    private TextInputEditText txtPasswordAgain;

    private Button btnRegister;
    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister_screen);
        txtUserName =(TextInputEditText) findViewById(R.id.txtRGUserName);
        txtPassword =(TextInputEditText) findViewById(R.id.txtRGPassword);
        txtPasswordAgain =(TextInputEditText) findViewById(R.id.txtRGPasswordAgain);
        btnRegister =(Button) findViewById(R.id.btnRegister);
        // create the instance of Databse
        engLishAppDatabaseAdapter =new EngLishAppDatabaseAdapter(getApplicationContext());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUserName.getText().toString().trim().equals("")){
                    Utils.showToast(getBaseContext(),"Tài khoản rỗng");
                }
                else{
                    if(txtPassword.getText().toString().trim().equals("")){
                        Utils.showToast(getBaseContext(),"Mật khẩu rỗng");
                    }
                    else{
                        if(txtPassword.getText().toString().trim().equals(txtPasswordAgain.getText().toString().trim())){
                            engLishAppDatabaseAdapter.insertUser(txtUserName.getText().toString().trim(),txtPassword.getText().toString().trim(),"1","1");

                            if(engLishAppDatabaseAdapter.isCheckCreateUser==true){
                                //Lấy lại dữ liệu sau khi thêm 1 tài khoản mới
                                try {
                                    User.users = engLishAppDatabaseAdapter.getRowUser();
                                    Topic.topics =engLishAppDatabaseAdapter.getRowTopic();
                                    Question.questions = engLishAppDatabaseAdapter.getRowAllTopicQuestion();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                createData();
                                //back lại màn hình đăng nhập
                                onBackPressed();
                                Utils.showToast(getBaseContext(),"Tạo tài khoản thành công !");
                            }
                            else{
                                Utils.showToast(getBaseContext(),"Tài khoản đã tồn tại !");
                            }


                        } else {
                            Utils.showToast(getBaseContext(),"Mật khẩu không giống nhau !");
                        }
                    }
                }


            }
        });
    }

    private void createData(){
        if(Topic.topics.size()==0){
            engLishAppDatabaseAdapter.insertTopic("Chào Hỏi","lv1.png");
            engLishAppDatabaseAdapter.insertTopic("Màu Sắc","lv2.png");
            engLishAppDatabaseAdapter.insertTopic("Quốc Gia","lv3.png");
            engLishAppDatabaseAdapter.insertTopic("Trường Học","lv4.png");
            engLishAppDatabaseAdapter.insertTopic("Thời Tiết","lv5.png");
        }
        if(Question.questions.size()==0){
            // chủ đề 1
            String [] question =
                    {       "Hello là gì ?",
                            "Good morning là gì ?",
                            "Good afternoon là gì ?",
                            "Nice to .... you",
                            ".... My name Tom",
                            "What do you do ?",
                            "Tôi khỏe cảm ơn",
                            "Tôi làm công nghệ thông tin",
                            "How are you?",
                            "How are you doing?"
                    };
            for (int i = 0; i < 10; i++) {
                engLishAppDatabaseAdapter.insertQuestion(1,question[i]);
            }
        }


        try {
            Topic.topics =engLishAppDatabaseAdapter.getRowTopic();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i =0;i< Topic.topics.size();i++){
            engLishAppDatabaseAdapter.insertUserTopic(User.users.get(User.users.size()-1).getID(),Topic.topics.get(i).getId_topic(),0);
        }


    }

}