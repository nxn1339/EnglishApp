package com.example.appenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appenglish.Model.PlayScreen;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView rcvPlayScreen;
    private View view;

    private PlayAdapter playAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_home, container, false);
        rcvPlayScreen = view.findViewById(R.id.rcvPlayScreen);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvPlayScreen.setLayoutManager(linearLayoutManager);
        playAdapter = new PlayAdapter();
        playAdapter.setData(getListPlayScreen(), new PlayAdapter.IClickPlayLevelListener() {
            @Override
            public void onClickPlayLevel(ImageView imgPlay, PlayScreen playScreen) {
                Toast.makeText(getContext(),playScreen.getID(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), LearningScreen.class);
                intent.putExtra("id",playScreen.getID());
                startActivity(intent);
            }
        });
        rcvPlayScreen.setAdapter(playAdapter);
        return view;
    }

    private List<PlayScreen> getListPlayScreen(){
        List<PlayScreen> list = new ArrayList<>();
        list.add(new PlayScreen("1", R.drawable.lv1,"Chào Hỏi","0/10"));
        list.add(new PlayScreen("2", R.drawable.lv2,"Màu Sắc","0/10"));
        list.add(new PlayScreen("3", R.drawable.lv3,"Quốc Gia","0/10"));
        list.add(new PlayScreen("4", R.drawable.lv4,"Trường Học","0/10"));
        list.add(new PlayScreen("5", R.drawable.lv5,"Thời Tiết","0/10"));

        return list;
    }
}