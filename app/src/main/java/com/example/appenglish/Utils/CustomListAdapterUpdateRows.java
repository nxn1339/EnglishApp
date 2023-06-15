package com.example.appenglish.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.R;

import java.util.ArrayList;

public class CustomListAdapterUpdateRows extends BaseAdapter {
    Context c;
    ArrayList<Topic> topics;

    public CustomListAdapterUpdateRows(Context c, ArrayList<Topic> topics) {
        this.c = c;
        this.topics = topics;
    }

    @Override
    public int getCount() {
        return topics.size();
    }

    @Override
    public Object getItem(int i) {
        return topics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listviewupdate_row,viewGroup,false);
        }

        final EditText meditText1 = (EditText) view.findViewById(R.id.editText1);
        final EditText meditText2 = (EditText) view.findViewById(R.id.editText2);
        ImageView imgMore = view.findViewById(R.id.imgMore);

        final Topic topic= (Topic) this.getItem(i);
        meditText1.setText(topic.getTitle());
        meditText2.setText(topic.getImg());

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
                                String col1value = meditText1.getText().toString();
                                String col2value = meditText2.getText().toString();

                                engLishAppDatabaseAdapter.updateTopic(topic.getId_topic(),col1value,col2value);
                                break;
                            case R.id.mn_delete:
                                //xóa liên kết user topic
                                engLishAppDatabaseAdapter.deleteUserTopic(topic.getId_topic());
                                //xóa topic
                                engLishAppDatabaseAdapter.deleteTopic(topic.getId_topic());
                                topics.remove(i);
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