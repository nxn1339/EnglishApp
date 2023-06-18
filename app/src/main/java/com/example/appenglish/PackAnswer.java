package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.appenglish.Model.Answer;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.Utils.CustomListAnswerUpdateDeleteRows;
import com.example.appenglish.Utils.CustomListQuestionUpdateDeleteRows;
import com.example.appenglish.Utils.Utils;

public class PackAnswer extends AppCompatActivity {
    static ListView listView2;
    static CustomListAnswerUpdateDeleteRows updateAdapter;
    EditText txtIDQuestion, txtIDTopic, txtAnswer;
    Button btnAddAnswer;
    RadioButton rdBtnCorrect, rdBtnWrong;
    private int correct = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_answer);
        EngLishAppDatabaseAdapter engLishAppDatabaseAdapter = new EngLishAppDatabaseAdapter(getApplicationContext());
        listView2 = findViewById(R.id.listupdateviewID2);
        txtIDQuestion = findViewById(R.id.txtIDQuestion);
        txtIDTopic = findViewById(R.id.txtIDTopic);
        txtAnswer = findViewById(R.id.txtAnswer);
        btnAddAnswer = findViewById(R.id.btnAddAnswer);
        rdBtnCorrect = findViewById(R.id.rdBtnCorrect);
        rdBtnWrong = findViewById(R.id.rdBtnWrong);

        try {
            Answer.answers = engLishAppDatabaseAdapter.getRowAllAnswer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAddAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtIDQuestion.getText().toString().trim().equals("") || txtIDTopic.getText().toString().trim().equals("") || txtAnswer.getText().toString().trim().equals("") || correct < 0 || correct > 1) {
                    Utils.showToast(getApplicationContext(), "Dữ liệu của bạn còn thiếu");
                } else {
                    engLishAppDatabaseAdapter.insertAnswer(Integer.parseInt(txtIDQuestion.getText().toString().trim()), Integer.parseInt(txtIDTopic.getText().toString().trim()), txtAnswer.getText().toString().trim(), correct);
                    //load lại acctivity
                    recreate();
                }

            }
        });

        rdBtnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct = 1;
            }
        });

        rdBtnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct = 0;
            }
        });
        updateAdapter = new CustomListAnswerUpdateDeleteRows(this, Answer.answers);
        listView2.setAdapter(updateAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}