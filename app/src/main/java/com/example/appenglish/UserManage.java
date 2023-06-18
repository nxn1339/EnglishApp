package com.example.appenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.Model.User;
import com.example.appenglish.Utils.CustomListUserUpdateDeleteRows;
import com.example.appenglish.Utils.Utils;


public class UserManage extends AppCompatActivity {
    static ListView listView3;
    static CustomListUserUpdateDeleteRows updateAdapter;
    ImageView imgViewTopic;
    Button btnPickIMG,btnAddUser;
    EditText txtUserNameMG,txtPasswordMG,txtAnswer;
    RadioButton rdBtnAdmin,rdBtnUser;
    private int role=100;
    private String img ="";
    private static final int REQUEST_SELECT_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);
        EngLishAppDatabaseAdapter engLishAppDatabaseAdapter = new EngLishAppDatabaseAdapter(getApplicationContext());
        listView3 = findViewById(R.id.listupdateviewID3);
        imgViewTopic = findViewById(R.id.imgViewTopic);
        btnPickIMG = findViewById(R.id.btnPickIMG);
        btnAddUser = findViewById(R.id.btnAddUser);
        txtUserNameMG = findViewById(R.id.txtUserNameMG);
        txtPasswordMG = findViewById(R.id.txtPasswordMG);
        txtAnswer = findViewById(R.id.txtAnswer);
        rdBtnAdmin = findViewById(R.id.rdBtnAdmin);
        rdBtnUser = findViewById(R.id.rdBtnUser);
        //lấy dữ liệu đầu

        try {
            User.users = engLishAppDatabaseAdapter.getRowUser();
            Topic.topics = engLishAppDatabaseAdapter.getRowTopic();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //nút chọn ảnh
        btnPickIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });
        rdBtnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // role =0 là admin
                role = 0;
            }
        });

        rdBtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //role = 1 là user
                role = 1;
            }
        });

        //nút thêm user
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check nếu rỗng thông báo cho người dùng
                if(txtUserNameMG.getText().toString().trim().equals("") || txtPasswordMG.getText().toString().trim().equals("") || role<0 || role>1){
                    //thông báo cho người dùng
                    Utils.showToast(getApplicationContext(),"Dữ liệu của bạn còn thiếu");
                }
                else {
                    //thêm user vào sqline
                    engLishAppDatabaseAdapter.insertUser(txtUserNameMG.getText().toString().trim(),txtPasswordMG.getText().toString().trim(),img,String.valueOf(role));

                    //lấy lại dữ liệu để thêm liên kết
                    try {
                        User.users = engLishAppDatabaseAdapter.getRowUser();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //thêm liên kết gói câu hỏi cho người dùng
                    for(int i=0;i<Topic.topics.size();i++){
                        //thêm dữ liệu liên kết gói câu hỏi và user
                        engLishAppDatabaseAdapter.insertUserTopic(User.users.get(User.users.size()-1).getID(),Topic.topics.get(i).getId_topic(),0);
                    }
                    //load lại acctivity
                    recreate();
                }
            }
        });

        updateAdapter = new CustomListUserUpdateDeleteRows(this, User.users);
        listView3.setAdapter(updateAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).into(imgViewTopic);
            //lấy đường link để cho vào database
            img = selectedImageUri.toString();
        }
    }
}