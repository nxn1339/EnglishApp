package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    Button btnPackTopic,btnPackQuestion,btnPackAnswer,btnUser,btnLogoutADM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btnPackTopic = findViewById(R.id.btnPackTopic);
        btnPackQuestion = findViewById(R.id.btnPackQuestion);
        btnPackAnswer = findViewById(R.id.btnPackAnswer);
        btnUser = findViewById(R.id.btnUser);
        btnLogoutADM = findViewById(R.id.btnLogoutADM);

        btnPackTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,PackTopic.class);
                startActivity(intent);
            }
        });
        btnPackQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,PackQuestion.class);
                startActivity(intent);
            }
        });

        //nút đăng xuất
        btnLogoutADM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPackAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this,PackAnswer.class);
                startActivity(intent);
            }
        });
    }
}