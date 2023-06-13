package com.example.appenglish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appenglish.Model.Answer;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.Model.UserTopic;
import com.example.appenglish.Utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

public class LearningScreen extends AppCompatActivity {
    TextView textView,tvAnswerCorrect;
    Button btnVerify, btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    LinearLayout lineSelect, lineInput;
    ProgressBar progressBar;
    RelativeLayout RLnotify;
    ImageView imgViewClose;
    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;
    private PlayAdapter playAdapter;
    EditText txtAnswer;
    private int i = 0, index = 100, point = 0, j = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_screen);
        textView = findViewById(R.id.tvQuestion);
        btnVerify = findViewById(R.id.btnVerify);

        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);
        lineSelect = findViewById(R.id.lineSelect);
        lineInput = findViewById(R.id.lineInput);
        txtAnswer = findViewById(R.id.txtAnswer);
        imgViewClose = findViewById(R.id.imgViewClose);
        progressBar = findViewById(R.id.prBarQuestion);


        try {
            Question.questions = engLishAppDatabaseAdapter.getRowOneTopicQuestion(Integer.parseInt(getIntent().getStringExtra("id")));
            Answer.answers = engLishAppDatabaseAdapter.getRowAnswer(j,Integer.parseInt(getIntent().getStringExtra("id")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //hiển thị cậu hỏi đầu tiên
        textView.setText(Question.questions.get(i).getQuestion());
        //hiển thì cậu trả lời
        if (Question.questions.get(i).getType() == 1) {
            btnAnswer1.setText(Answer.answers.get(0).getAnswer());
            btnAnswer2.setText(Answer.answers.get(1).getAnswer());
            btnAnswer3.setText(Answer.answers.get(2).getAnswer());
            btnAnswer4.setText(Answer.answers.get(3).getAnswer());
            //ẩn hiện chọn đáp án hoặc điền
            lineSelect.setVisibility(View.VISIBLE);
            lineInput.setVisibility(View.GONE);
        } else {
            //ẩn hiện chọn đáp án hoặc điền
            lineSelect.setVisibility(View.GONE);
            lineInput.setVisibility(View.VISIBLE);
        }
        //nút xác nhận câu hỏi
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HUHU", String.valueOf(index));
                if (index < 4) {
                    //check kết quả
                    if (Question.questions.get(i).getType() == 1) {
                        //kiểm tra câu hỏi
                        try {
                            checkQuestion(index);
                        } catch (Exception e) {
                            Log.i("Lỗi", "LỖI");
                        }
                    } else {
                        checkQuestionInput(txtAnswer.getText().toString().trim());
                    }
                    // i là số trong array list bắt đầu =0
                    // j là id của câu hỏi bắt đầu = 1
                    j++;
                    i++;
                    // nếu là câu hỏi cuối không cần đọc database đưa ra nữa (khi i==Question.questions.size() )
                    if (i != Question.questions.size()) {
                        //chuyển màu hover
                        btnAnswer1.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                        btnAnswer2.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                        btnAnswer3.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                        btnAnswer4.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                        //hiển thi câu hỏi tiếp theo
                        textView.setText(Question.questions.get(i).getQuestion());

                        //lấy câu trả lời cho vào 4 nút
                        try {
                            Answer.answers = engLishAppDatabaseAdapter.getRowAnswer(j,Integer.parseInt(getIntent().getStringExtra("id")));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        if (Question.questions.get(i).getType() == 1) {
                            //type ==1 thì là chọn 4 đáp án
                            //type ==2 thì là điền đáp án

                            //reset chọn câu trả lời
                            index = 100;
                            btnAnswer1.setText(Answer.answers.get(0).getAnswer());
                            btnAnswer2.setText(Answer.answers.get(1).getAnswer());
                            btnAnswer3.setText(Answer.answers.get(2).getAnswer());
                            btnAnswer4.setText(Answer.answers.get(3).getAnswer());

                            //ẩn hiện chọn đáp án hoặc điền
                            lineSelect.setVisibility(View.VISIBLE);
                            lineInput.setVisibility(View.GONE);

                        } else {
                            //reset câu trả lời
                            txtAnswer.setText("");
                            //ẩn hiện chọn đáp án hoặc điền
                            lineSelect.setVisibility(View.GONE);
                            lineInput.setVisibility(View.VISIBLE);

                        }
                    }

                    //thanh câu hỏi
                    progressBar.setProgress((i + 1) * 10);
                    //hết câu hỏi đưa ra màn hình home
                    if (i == Question.questions.size()) {
                        //cập nhật lại điểm của topic
                        try {
                            engLishAppDatabaseAdapter.updatePoint(LoginActivity.user.getID(),Integer.parseInt(getIntent().getStringExtra("id")),point);
                            UserTopic.userTopics =engLishAppDatabaseAdapter.getRowUserTopic();
                        }
                        catch (Exception e){

                        }
                        //show điểm cho người dùng
                        showDialog();
                    }
                }
            }
        });
        //nút câu hỏi 1
        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dổi mầu hover
                btnAnswer1.setBackgroundColor(getResources().getColor(R.color.color_select));
                btnAnswer2.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer3.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer4.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                //chọn câu hỏi đầu
                index = 0;
            }
        });
        //nút câu hỏi 2
        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //đổi màu hover
                btnAnswer1.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer2.setBackgroundColor(getResources().getColor(R.color.color_select));
                btnAnswer3.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer4.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                //câu hỏi thứ 2
                index = 1;

            }
        });
        //nút câu hỏi 3
        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //đổi màu hover
                btnAnswer1.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer2.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer3.setBackgroundColor(getResources().getColor(R.color.color_select));
                btnAnswer4.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                //câu hỏi thứ 3
                index = 2;
            }
        });
        //nút câu hỏi 4
        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //đổi màu hover
                btnAnswer1.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer2.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer3.setBackgroundColor(getResources().getColor(R.color.color_no_select));
                btnAnswer4.setBackgroundColor(getResources().getColor(R.color.color_select));
                //câu hỏi thứ 4
                index = 3;
            }
        });

        //nút thoát
        imgViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //kiểm tra câu hỏi chọn (chọn 4 đáp án)
    private void checkQuestion(int index) {
        //đáp án đúng lấy hiển thị ở snackbar
        String correctAnswer = "";
        for (int j = 0; j < 4; j++) {
            if (Answer.answers.get(j).getCorrect() == 1) {
                correctAnswer = Answer.answers.get(j).getAnswer();
            }
        }

        if (Answer.answers.get(index).getCorrect() == 1) {
            //trả lời đúng tăng 1 điểm
            point += 1;
            //hiển thị snackbar thông báo đáp án đúng
            showSnackBar("Đáp án chính xác",true);
        } else {
            //hiển thị snackbar thông báo đáp án sai và đưa ra câu trả lời đúng
            showSnackBar("Đáp án phải là " + correctAnswer,false);
        }
    }

    //kiểm tra câu hỏi nhập đáp án
    private void checkQuestionInput(String answer) {
        if (answer.equals(Answer.answers.get(0).getAnswer().trim())) {
            // trả lời đúng tăng 1 điểm
            point += 1;
            //hiển thị snackbar thông báo đáp án đúng
            showSnackBar("Đáp án chính xác",true);
        } else {
            //hiển thị snackbar thông báo đáp án sai và đưa ra câu trả lời đúng
            showSnackBar("Đáp án phải là " + Answer.answers.get(0).getAnswer(),false);
        }
    }

    private void showSnackBar(String title,Boolean isCorrect) {
        Snackbar snackbar = Snackbar.make(btnVerify, title, Snackbar.LENGTH_INDEFINITE);
        View snackbar_custom = getLayoutInflater().inflate(R.layout.snackbar_custom,null);
        tvAnswerCorrect = snackbar_custom.findViewById(R.id.tvAnserCorrect);
        RLnotify = snackbar_custom.findViewById(R.id.RLnotify);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        (snackbar_custom.findViewById(R.id.btnContinue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        if(isCorrect==true){
            RLnotify.setBackgroundColor(getResources().getColor(R.color.correct));
        }
        else {
            RLnotify.setBackgroundColor(getResources().getColor(R.color.wrong));
        }
        tvAnswerCorrect.setText(title);
        snackbarLayout.addView(snackbar_custom,0);
        snackbar.show();
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LearningScreen.this);
        builder.setTitle("Điểm của bạn");
        builder.setMessage(String.valueOf(point)+"/10");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng nhấn nút Đồng ý
                //tạo lại acctivity rồi back lại trang home
                Intent intent =new Intent(LearningScreen.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}