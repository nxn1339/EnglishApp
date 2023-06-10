package com.example.appenglish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appenglish.Model.EngLishAppDatabaseAdapter;
import com.example.appenglish.Model.PlayScreen;
import com.example.appenglish.Model.Topic;
import com.example.appenglish.Model.User;
import com.example.appenglish.Model.UserTopic;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView rcvPlayScreen;
    private View view;
    private TextView tvLevel;
    private PlayAdapter playAdapter;
    EngLishAppDatabaseAdapter engLishAppDatabaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_home, container, false);
        rcvPlayScreen = view.findViewById(R.id.rcvPlayScreen);
        tvLevel = view.findViewById(R.id.tvLevel);
        tvLevel.setText(LoginActivity.user.getLv());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvPlayScreen.setLayoutManager(linearLayoutManager);
        playAdapter = new PlayAdapter();
        //Lấy dữ liệu
        try {
            User.users = engLishAppDatabaseAdapter.getRowUser();
            Topic.topics =engLishAppDatabaseAdapter.getRowTopic();
            UserTopic.userTopics =engLishAppDatabaseAdapter.getRowUserTopic();
        } catch (JSONException e) {
            Log.i("Lỗi ở đăng nhập","Sửa đi");
            e.printStackTrace();
        }
        playAdapter.setData(getListPlayScreen(), new PlayAdapter.IClickPlayLevelListener() {
            @Override
            public void onClickPlayLevel(ImageView imgPlay, PlayScreen playScreen) {
                Intent intent = new Intent(getActivity(), LearningScreen.class);
                intent.putExtra("id",String.valueOf(playScreen.getID()));
                startActivity(intent);
            }
        });
        rcvPlayScreen.setAdapter(playAdapter);
        return view;
    }

    private List<PlayScreen> getListPlayScreen(){
        List<PlayScreen> list = new ArrayList<>();
        for(int i = 0; i< Topic.topics.size(); i++){
            list.add(new PlayScreen(Topic.topics.get(i).getId_topic(),images(i),Topic.topics.get(i).getTitle(), UserTopic.userTopics.get(i).getPoint() +"/10"));
        }
        return list;
    }
    private int images(int i){
        switch (i){
            case 0:
                return R.drawable.lv1;
            case 1:
                return R.drawable.lv2;
            case 2:
                return R.drawable.lv3;
            case 3:
                return R.drawable.lv4;
            case 4:
                return R.drawable.lv5;
            default:
                return R.drawable.lv1;

        }
    }
}