package com.example.appenglish;

import androidx.appcompat.app.ActionBar;
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

import com.bumptech.glide.Glide;
import com.example.appenglish.Model.Database;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.Model.User;
import com.example.appenglish.Utils.CustomListAdapterUpdateRows;

public class PackTopic extends AppCompatActivity {
    static ListView listView ;
    static CustomListAdapterUpdateRows updateAdapter;

    EditText txtTitle;
    Button btnAdd,btnPickIMG;
    ImageView imageView;
    //ảnh
    private String img ="";
    private static final int REQUEST_SELECT_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_topic);
        EngLishAppDatabaseAdapter engLishAppDatabaseAdapter = new EngLishAppDatabaseAdapter(getApplicationContext());
        txtTitle = findViewById(R.id.txtTitle);
        btnAdd = findViewById(R.id.btnAdd);
        btnPickIMG = findViewById(R.id.btnPickIMG);
        imageView = findViewById(R.id.imgViewTopic);
        try {
            User.users = engLishAppDatabaseAdapter.getRowUser();
            Topic.topics = engLishAppDatabaseAdapter.getRowTopic();
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnPickIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_SELECT_IMAGE);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engLishAppDatabaseAdapter.insertTopic(txtTitle.getText().toString().trim(),img);
                //đọc lại số gói câu hỏi tạo điểm cho user
                try {
                    Topic.topics = engLishAppDatabaseAdapter.getRowTopic();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //tạo liên kết bảng và điểm cho user
                for (int i = 0; i <User.users.size(); i++) {
                    engLishAppDatabaseAdapter.insertUserTopic(User.users.get(i).getID(),Topic.topics.get(Topic.topics.size()-1).getId_topic(),0);
                }
                //làm trống text
                txtTitle.setText("");
                //load lại acctivity
                recreate();
            }
        });

        updateAdapter = new CustomListAdapterUpdateRows(this, Topic.topics );
        listView = (ListView) findViewById(R.id.listupdateviewID);
        listView.setAdapter(updateAdapter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Update User");
        }

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Glide.with(this).load(selectedImageUri).into(imageView);
            //lấy đường link để cho vào database
            img = selectedImageUri.toString();
        }
    }

}