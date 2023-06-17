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
import com.example.appenglish.Model.User;
import com.example.appenglish.R;

import java.util.ArrayList;

public class CustomListUserUpdateDeleteRows extends BaseAdapter {
    Context c;
    ArrayList<User> users;
    public CustomListUserUpdateDeleteRows(Context c, ArrayList<User> users) {
        this.c = c;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.listview_user_row,viewGroup,false);
        }

        final EditText meditText1 = (EditText) view.findViewById(R.id.editText1);
        final EditText meditText2 = (EditText) view.findViewById(R.id.editText2);
        final EditText meditText3 = (EditText) view.findViewById(R.id.editText3);
        final EditText meditText4 = (EditText) view.findViewById(R.id.editText4);
        final EditText meditText5 = (EditText) view.findViewById(R.id.editText5);
        ImageView imgMore = view.findViewById(R.id.imgMore);

        final User user= (User) this.getItem(i);
        meditText1.setText(user.getUser_name());
        meditText2.setText(user.getPassword());
        meditText3.setText(user.getImg_avatar());
        meditText4.setText(user.getFull_name());
        meditText5.setText(user.getRole());

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
                                String col2value =meditText2.getText().toString();
                                String col3value = meditText3.getText().toString();
                                String col4value = meditText4.getText().toString();
                                String col5value = meditText5.getText().toString();
                                engLishAppDatabaseAdapter.updateUserMG(user.getID(),col1value,col2value,col3value,col4value,col5value);
                                break;
                            case R.id.mn_delete:
                                //xóa liên kết user topic
                                engLishAppDatabaseAdapter.deleteUserTopicMG(user.getID());
                               //xóa user
                                engLishAppDatabaseAdapter.deleteUser(user.getID());
                                users.remove(i);
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
