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
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.Utils.CustomListQuestionUpdateDeleteRows;
import com.example.appenglish.Utils.Utils;


public class PackQuestion extends AppCompatActivity {
    static ListView listView1 ;
    Button btnAddQuestion;
    EditText txtIDTopic,txtQuestion;
    RadioButton rdBtnSelect,tbBtnFill;
    static CustomListQuestionUpdateDeleteRows updateAdapter;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EngLishAppDatabaseAdapter engLishAppDatabaseAdapter = new EngLishAppDatabaseAdapter(getApplicationContext());
        setContentView(R.layout.activity_pack_question);
        txtIDTopic = findViewById(R.id.txtIDTopic);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnAddQuestion = findViewById(R.id.btnAddQuestion);
        rdBtnSelect = findViewById(R.id.rdBtnSelect);
        tbBtnFill = findViewById(R.id.rdBtnFill);
        listView1 = (ListView) findViewById(R.id.listupdateviewID1);
        try {
            Question.questions = engLishAppDatabaseAdapter.getRowAllTopicQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtIDTopic.getText().toString().trim().equals("")||txtQuestion.getText().toString().trim().equals("")||type<1 ||type>2){
                    Utils.showToast(getApplicationContext(),"Dữ liệu của bạn còn thiếu");
                }
                else {
                    engLishAppDatabaseAdapter.insertQuestion(Integer.parseInt(txtIDTopic.getText().toString().trim()), txtQuestion.getText().toString().trim(), type);
                    //load lại acctivity
                    recreate();
                }
            }
        });
        rdBtnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
            }
        });

        tbBtnFill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
            }
        });

        updateAdapter = new CustomListQuestionUpdateDeleteRows(this, Question.questions );

        listView1.setAdapter(updateAdapter);
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

}