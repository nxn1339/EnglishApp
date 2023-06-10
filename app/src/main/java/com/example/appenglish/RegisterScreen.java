package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appenglish.Model.Answer;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.Model.User;
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
                            "Hello My .... Tom",
                            "What do you do ?",
                            "Tôi khỏe cảm ơn",
                            "Tôi học công nghệ thông tin",
                            "How are you?",
                            "How are you doing?"
                    };
            int [] type ={1,1,1,2,2,1,1,1,1,1};
            for (int i = 0; i < 10; i++) {
                engLishAppDatabaseAdapter.insertQuestion(1,question[i],type[i]);
            }
            try {
                Question.questions =engLishAppDatabaseAdapter.getRowAllTopicQuestion();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Answer[] answer =
                    {       new Answer(0,1,"Xin chào", 1),
                            new Answer(0,1,"Chào buổi sáng", 0),
                            new Answer(0,1,"Chào buổi chiều", 0),
                            new Answer(0,1,"Ai vậy", 0),

                            new Answer(0,2,"Tôi khỏe", 0),
                            new Answer(0,2,"Chào buổi sáng", 1),
                            new Answer(0,2,"Xin chào", 0),
                            new Answer(0,2,"Rất vui gặp bạn", 0),

                            new Answer(0,3,"Rất vui gặp bạn", 0),
                            new Answer(0,3,"Chào buổi chiều", 1),
                            new Answer(0,3,"Bạn là ai", 0),
                            new Answer(0,3,"Xin chào", 0),

                            new Answer(0,4,"meet", 1),

                            new Answer(0,5,"name", 1),

                            new Answer(0,6,"Bạn làm nghề gì", 1),
                            new Answer(0,6,"Bạn đến từ đâu", 0),
                            new Answer(0,6,"Bạn tên gì", 0),
                            new Answer(0,6,"Bạn bao nhiều tuổi", 0),

                            new Answer(0,7,"What is your name", 0),
                            new Answer(0,7,"Where are you from", 0),
                            new Answer(0,7,"I'm fine, thanks", 1),
                            new Answer(0,7,"Are you ok", 0),

                            new Answer(0,8,"I study information technology", 1),
                            new Answer(0,8,"I'm teacher", 0),
                            new Answer(0,8,"I am a student", 0),
                            new Answer(0,8,"I learn English", 0),

                            new Answer(0,9,"Are you ok", 0),
                            new Answer(0,9,"I'm fine, thanks", 1),
                            new Answer(0,9,"Who are you", 0),
                            new Answer(0,9,"Where are you from", 0),

                            new Answer(0,10,"Bạn làm nghề gì", 0),
                            new Answer(0,10,"Bạn đến từ đâu", 0),
                            new Answer(0,10,"Bạn tên gì", 0),
                            new Answer(0,10,"Bạn đang làm gì", 1),




                    };
            for(int i=0;i<answer.length;i++){
                engLishAppDatabaseAdapter.insertAnswer(answer[i].getId_question(),answer[i].getAnswer(),answer[i].getCorrect());
            }

        }


        try {
            User.users = engLishAppDatabaseAdapter.getRowUser();
            Topic.topics =engLishAppDatabaseAdapter.getRowTopic();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i =0;i< Topic.topics.size();i++){
            engLishAppDatabaseAdapter.insertUserTopic(User.users.get(User.users.size()-1).getID(),Topic.topics.get(i).getId_topic(),0);
        }


    }

}