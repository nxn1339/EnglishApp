package com.example.appenglish.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.R;
import java.util.ArrayList;

public class CustomListQuestionUpdateDeleteRows extends BaseAdapter {
    Context c;
    ArrayList<Question> questions;
    public CustomListQuestionUpdateDeleteRows(Context c, ArrayList<Question> questions) {
        this.c = c;
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int i) {
        return questions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listview_question_row,viewGroup,false);
        }

        final EditText meditText1 = (EditText) view.findViewById(R.id.editText1);
        final EditText meditText2 = (EditText) view.findViewById(R.id.editText2);
        final EditText meditText3 = (EditText) view.findViewById(R.id.editText3);
        ImageView imgMore = view.findViewById(R.id.imgMore);

        final Question question= (Question) this.getItem(i);
        meditText1.setText(String.valueOf(question.getId_topic()));
        meditText2.setText(question.getQuestion());
        meditText3.setText(String.valueOf(question.getType()));

        imgMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), imgMore);
                popupMenu.getMenuInflater().inflate(R.menu.update_delete,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        EngLishAppDatabaseAdapter engLishAppDatabaseAdapter = new EngLishAppDatabaseAdapter(c);
                        switch (item.getItemId()){
                            case R.id.mn_update:
                                int col1value = Integer.parseInt(meditText1.getText().toString());
                                String col2value = meditText2.getText().toString();
                                int col3value = Integer.parseInt(meditText3.getText().toString());
                                engLishAppDatabaseAdapter.updateQuest(question.getId_question(),col1value,col2value,col3value);
                                break;
                            case R.id.mn_delete:
                                //xóa topic
                                engLishAppDatabaseAdapter.deleteQuestion(question.getId_question());
                                questions.remove(i);
                                notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });

        return view;
    }
}
