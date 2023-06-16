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

import com.example.appenglish.Model.Answer;
import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Question;
import com.example.appenglish.R;

import java.util.ArrayList;

public class CustomListAnswerUpdateDeleteRows extends BaseAdapter {
    Context c;
    ArrayList<Answer> answers;
    public CustomListAnswerUpdateDeleteRows(Context c, ArrayList<Answer> answers) {
        this.c = c;
        this.answers = answers;
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int i) {
        return answers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listview_answer_row,viewGroup,false);
        }

        final EditText meditText1 = (EditText) view.findViewById(R.id.editText1);
        final EditText meditText2 = (EditText) view.findViewById(R.id.editText2);
        final EditText meditText3 = (EditText) view.findViewById(R.id.editText3);
        final EditText meditText4 = (EditText) view.findViewById(R.id.editText4);
        ImageView imgMore = view.findViewById(R.id.imgMore);

        final Answer answer= (Answer) this.getItem(i);
        meditText1.setText(String.valueOf(answer.getId_question()));
        meditText2.setText(String.valueOf(answer.getId_topic()));
        meditText3.setText(answer.getAnswer());
        meditText4.setText(String.valueOf(answer.getCorrect()));

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
                                int col2value = Integer.parseInt(meditText2.getText().toString());
                                String col3value = meditText3.getText().toString();
                                int col4value = Integer.parseInt(meditText4.getText().toString());
                                engLishAppDatabaseAdapter.updateAnswer(answer.getId_answer(),col1value,col2value,col3value,col4value);
                                break;
                            case R.id.mn_delete:
                                //xóa topic
                                engLishAppDatabaseAdapter.deleteQuestion(answer.getId_answer());
                                answers.remove(i);
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
