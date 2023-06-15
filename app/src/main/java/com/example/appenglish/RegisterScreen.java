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
        engLishAppDatabaseAdapter =new EngLishAppDatabaseAdapter(RegisterScreen.this);
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
                            engLishAppDatabaseAdapter.insertUser(txtUserName.getText().toString().trim(),txtPassword.getText().toString().trim(),"","1");

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
            Question [] question =
                    {
                            //topic 1
                            new Question(1,1,"Hello là gì ?",1),
                            new Question(1,1,"Good morning là gì ?",1),
                            new Question(1,1,"Good afternoon là gì ?",1),
                            new Question(1,1, "Nice to .... you",2),
                            new Question(1,1,"Hello My .... Tom",2),
                            new Question(1,1,"What do you do ?",1),
                            new Question(1,1,"Tôi khỏe cảm ơn",1),
                            new Question(1,1,"Tôi học công nghệ thông tin",1),
                            new Question(1,1,"How are you?",1),
                            new Question(1,1,"How are you doing?",1),

                            //topic 2
                            new Question(1,2,"màu đỏ là gì?",1),
                            new Question(1,2,"blue ?",1),
                            new Question(1,2,"yellow ?",1),
                            new Question(1,2, "a .... shirt (một chiếc áo đỏ)",2),
                            new Question(1,2,"màu xanh lá?",2),
                            new Question(1,2,"màu xanh dương",1),
                            new Question(1,2,"i have a ... hat (tôi có một chiếc mũ đen)",2),
                            new Question(1,2,"pink là gì?",1),
                            new Question(1,2,"grey là gì?",1),
                            new Question(1,2,"color là gì?",1),

                            //topic 3
                            new Question(1,3,"người Việt Nam là ?",1),
                            new Question(1,3,"người nhật bản là ?",1),
                            new Question(1,3,"I love Vietnam",1),
                            new Question(1,3, "Việt Nam số một ?",2),
                            new Question(1,3,"The capital of .... is Hanoi",2),
                            new Question(1,3,"người hàn quốc ?",1),
                            new Question(1,3,"French ?",1),
                            new Question(1,3,"where are you from ?",1),
                            new Question(1,3,"I come from .... ! (Tôi đến từ Việt Nam)",2),
                            new Question(1,3,"Portuguese ?",1),

                            //topic 4
                            new Question(1,4,"pen là ?",1),
                            new Question(1,4,"thước kẻ là ?",1),
                            new Question(1,4,"book là ?",1),
                            new Question(1,4, "i have three ....(tôi có 3 cái bút)",2),
                            new Question(1,4,"I have a .... (tôi có một cái bút)",2),
                            new Question(1,4,"school là ?",1),
                            new Question(1,4,"class ?",1),
                            new Question(1,4,"Academy là ?",1),
                            new Question(1,4,"Desk là ?",1),
                            new Question(1,4,"Library là ?",1),

                            //topic 5
                            new Question(1,5,"Hot là ?",1),
                            new Question(1,5,"Cold là ?",1),
                            new Question(1,5,"Snow là ?",1),
                            new Question(1,5, "Rain",1),
                            new Question(1,5,"tyết",2),
                            new Question(1,5,"nóng",2),
                            new Question(1,5,"lạnh",2),
                            new Question(1,5,"mưa",2),
                            new Question(1,5,"Windy",1),
                            new Question(1,5,"cloud",1),

                    };
            for (int i = 0; i < question.length; i++) {
                engLishAppDatabaseAdapter.insertQuestion(question[i].getId_topic(),question[i].getQuestion(),question[i].getType());
            }
            try {
                Question.questions =engLishAppDatabaseAdapter.getRowAllTopicQuestion();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Answer[] answer =
                    {
                            //Topic 1
                            new Answer(0,1, 1, "Xin chào", 1),
                            new Answer(0,1, 1, "Chào buổi sáng", 0),
                            new Answer(0,1, 1, "Chào buổi chiều", 0),
                            new Answer(0,1, 1, "Ai vậy", 0),

                            new Answer(0,2, 1, "Tôi khỏe", 0),
                            new Answer(0,2, 1, "Chào buổi sáng", 1),
                            new Answer(0,2, 1, "Xin chào", 0),
                            new Answer(0,2, 1, "Rất vui gặp bạn", 0),

                            new Answer(0,3, 1, "Rất vui gặp bạn", 0),
                            new Answer(0,3, 1, "Chào buổi chiều", 1),
                            new Answer(0,3, 1, "Bạn là ai", 0),
                            new Answer(0,3, 1, "Xin chào", 0),

                            new Answer(0,4, 1, "meet", 1),

                            new Answer(0,5, 1, "name", 1),

                            new Answer(0,6, 1, "Bạn làm nghề gì", 1),
                            new Answer(0,6, 1, "Bạn đến từ đâu", 0),
                            new Answer(0,6, 1, "Bạn tên gì", 0),
                            new Answer(0,6, 1, "Bạn bao nhiều tuổi", 0),

                            new Answer(0,7, 1, "What is your name", 0),
                            new Answer(0,7, 1, "Where are you from", 0),
                            new Answer(0,7, 1, "I'm fine, thanks", 1),
                            new Answer(0,7, 1, "Are you ok", 0),

                            new Answer(0,8, 1, "I study information technology", 1),
                            new Answer(0,8, 1, "I'm teacher", 0),
                            new Answer(0,8, 1, "I am a student", 0),
                            new Answer(0,8, 1, "I learn English", 0),

                            new Answer(0,9, 1, "Are you ok", 0),
                            new Answer(0,9, 1, "I'm fine, thanks", 1),
                            new Answer(0,9, 1, "Who are you", 0),
                            new Answer(0,9, 1, "Where are you from", 0),

                            new Answer(0,10, 1, "Bạn làm nghề gì", 0),
                            new Answer(0,10, 1, "Bạn đến từ đâu", 0),
                            new Answer(0,10, 1, "Bạn tên gì", 0),
                            new Answer(0,10, 1, "Bạn đang làm gì", 1),

                            //Topic 2
                            new Answer(0,1, 2, "red", 1),
                            new Answer(0,1, 2, "blue", 0),
                            new Answer(0,1, 2, "yellow", 0),
                            new Answer(0,1, 2, "pink", 0),

                            new Answer(0,2, 2, "đỏ", 0),
                            new Answer(0,2, 2, "xanh lá", 0),
                            new Answer(0,2, 2, "xanh dương", 1),
                            new Answer(0,2, 2, "đen", 0),

                            new Answer(0,3, 2, "xanh lá", 0),
                            new Answer(0,3, 2, "đỏ", 0),
                            new Answer(0,3, 2, "đen", 0),
                            new Answer(0,3, 2, "vàng", 1),

                            new Answer(0,4, 2, "red", 1),

                            new Answer(0,5, 2, "green", 1),

                            new Answer(0,6, 2, "red", 0),
                            new Answer(0,6, 2, "blue", 1),
                            new Answer(0,6, 2, "yellow", 0),
                            new Answer(0,6, 2, "pink", 0),

                            new Answer(0,7, 2, "black", 1),

                            new Answer(0,8, 2, "đỏ", 0),
                            new Answer(0,8, 2, "xanh dương", 0),
                            new Answer(0,8, 2, "vàng", 0),
                            new Answer(0,8, 2, "hồng", 1),

                            new Answer(0,9, 2, "đỏ", 0),
                            new Answer(0,9, 2, "xanh dương", 0),
                            new Answer(0,9, 2, "xám", 1),
                            new Answer(0,9, 2, "hồng", 0),

                            new Answer(0,10, 2, "màu sắc", 1),
                            new Answer(0,10, 2, "cái bút", 0),
                            new Answer(0,10, 2, "màu hồng", 1),
                            new Answer(0,10, 2, "cầu vồng", 0),

                            //Topic 3

                            new Answer(0,1, 3, "Vietnamese", 1),
                            new Answer(0,1, 3, "Vietnam", 0),
                            new Answer(0,1, 3, "Vietname", 0),
                            new Answer(0,1, 3, "Vietnamse", 0),

                            new Answer(0,2, 3, "Vietnamese", 0),
                            new Answer(0,2, 3, "Japan", 0),
                            new Answer(0,2, 3, "Japanese", 1),
                            new Answer(0,2, 3, "VietNam", 0),

                            new Answer(0,3, 3, "Tôi thích Việt Nam", 0),
                            new Answer(0,3, 3, "Tôi yêu Viêt Nam", 1),
                            new Answer(0,3, 3, "Tôi ở Việt Nam", 0),
                            new Answer(0,3, 3, "Tôi là người Việt Nam", 0),

                            new Answer(0,4, 3, "vietnam number one", 1),

                            new Answer(0,5, 3, "vietnam", 1),

                            new Answer(0,6, 3, "Korean", 1),
                            new Answer(0,6, 3, "Vietnamese", 0),
                            new Answer(0,6, 3, "Japanese", 0),
                            new Answer(0,6, 3, "Korea", 0),

                            new Answer(0,7, 3, "Người Pháp", 1),
                            new Answer(0,7, 3, "Người Việt", 0),
                            new Answer(0,7, 3, "Người Nhật", 0),
                            new Answer(0,7, 3, "Người Hàn Quốc", 0),

                            new Answer(0,8, 3, "Bạn là ai", 0),
                            new Answer(0,8, 3, "Bạn đến từ đâu", 1),
                            new Answer(0,8, 3, "Bạn tên là gì", 0),
                            new Answer(0,8, 3, "Bạn bao nhiêu tuổi", 0),

                            new Answer(0,9, 3, "vietnam", 1),

                            new Answer(0,10, 3, "Người Pháp", 0),
                            new Answer(0,10, 3, "Người Việt", 0),
                            new Answer(0,10, 3, "Người Bồ Đào Nha", 1),
                            new Answer(0,10, 3, "Người Hàn Quốc", 0),

                            //Topic 4
                            new Answer(0,1, 4, "cái bút", 1),
                            new Answer(0,1, 4, "cục tẩy", 0),
                            new Answer(0,1, 4, "cái thước kẻ", 0),
                            new Answer(0,1, 4, "quyển sách", 0),

                            new Answer(0,2, 4, "pen", 0),
                            new Answer(0,2, 4, "book", 0),
                            new Answer(0,2, 4, "ruler", 1),
                            new Answer(0,2, 4, "eraser", 0),

                            new Answer(0,3, 4, "cái bút", 0),
                            new Answer(0,3, 4, "cục tẩy", 0),
                            new Answer(0,3, 4, "cái thước kẻ", 0),
                            new Answer(0,3, 4, "quyển sách", 1),

                            new Answer(0,3, 4, "cái bút", 0),
                            new Answer(0,3, 4, "cục tẩy", 0),
                            new Answer(0,3, 4, "cái thước kẻ", 0),
                            new Answer(0,3, 4, "quyển sách", 1),

                            new Answer(0,4, 4, "pens", 1),

                            new Answer(0,5, 4, "pen", 1),

                            new Answer(0,6, 4, "cái bút", 0),
                            new Answer(0,6, 4, "Lớp học", 0),
                            new Answer(0,6, 4, "cái thước kẻ", 0),
                            new Answer(0,6, 4, "Trường học", 1),


                            new Answer(0,7, 4, "Học viện", 0),
                            new Answer(0,7, 4, "Lớp học", 1),
                            new Answer(0,7, 4, "quyển sách", 0),
                            new Answer(0,7, 4, "Trường học", 0),

                            new Answer(0,8, 4, "Học viện", 1),
                            new Answer(0,8, 4, "Lớp học", 0),
                            new Answer(0,8, 4, "quyển sách", 0),
                            new Answer(0,8, 4, "Trường học", 0),

                            new Answer(0,8, 4, "Học viện", 0),
                            new Answer(0,8, 4, "Lớp học", 1),
                            new Answer(0,8, 4, "quyển sách", 0),
                            new Answer(0,8, 4, "Trường học", 0),

                            new Answer(0,9, 4, "cái bút", 0),
                            new Answer(0,9, 4, "cái bàn", 1),
                            new Answer(0,9, 4, "quyển sách", 0),
                            new Answer(0,9, 4, "cánh cửa", 0),

                            new Answer(0,10, 4, "thư viện", 1),
                            new Answer(0,10, 4, "phòng học", 0),
                            new Answer(0,10, 4, "nhà vệ sinh", 0),
                            new Answer(0,10, 4, "phòng máy", 0),

                            //Topic 5
                            new Answer(0,1, 5, "nóng", 1),
                            new Answer(0,1, 5, "lạnh", 0),
                            new Answer(0,1, 5, "ấm", 0),
                            new Answer(0,1, 5, "mát", 0),

                            new Answer(0,2, 5, "nóng", 0),
                            new Answer(0,2, 5, "lạnh", 1),
                            new Answer(0,2, 5, "ấm", 0),
                            new Answer(0,2, 5, "mát", 0),

                            new Answer(0,3, 5, "lá cây", 0),
                            new Answer(0,3, 5, "tuyết", 1),
                            new Answer(0,3, 5, "nước", 0),
                            new Answer(0,3, 5, "đá", 0),

                            new Answer(0,4, 5, "mưa", 1),
                            new Answer(0,4, 5, "tuyết", 0),
                            new Answer(0,4, 5, "gió", 0),
                            new Answer(0,4, 5, "sương", 0),

                            new Answer(0,5, 5, "snow", 1),

                            new Answer(0,6, 5, "hot", 1),

                            new Answer(0,7, 5, "cold", 1),

                            new Answer(0,8, 5, "rain", 1),

                            new Answer(0,9, 5, "mưa", 0),
                            new Answer(0,9, 5, "tuyết", 0),
                            new Answer(0,9, 5, "gió", 1),
                            new Answer(0,9, 5, "sương", 0),

                            new Answer(0,10, 5, "mưa", 0),
                            new Answer(0,10, 5, "tuyết", 0),
                            new Answer(0,10, 5, "gió", 0),
                            new Answer(0,10, 5, "mây", 1),


                    };
            for(int i=0;i<answer.length;i++){
                engLishAppDatabaseAdapter.insertAnswer(answer[i].getId_question(),answer[i].getId_topic(),answer[i].getAnswer(),answer[i].getCorrect());
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