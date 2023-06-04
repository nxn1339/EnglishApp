package com.example.appenglish;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appenglish.Model.PlayScreen;
import com.example.appenglish.Model.Topic;

import java.util.List;

public class PlayAdapter extends RecyclerView.Adapter<PlayAdapter.PlayViewHolder>{
    private List<PlayScreen> listPlayScreen;
    private IClickPlayLevelListener iClickPlayLevelListener;
    public interface IClickPlayLevelListener{
        void onClickPlayLevel(ImageView imgPlay,PlayScreen playScreen);
    }
    public void setData(List<PlayScreen> list,IClickPlayLevelListener listener){
        this.listPlayScreen = list;
        this.iClickPlayLevelListener = listener;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_screen,parent,false);
        return new PlayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayViewHolder holder, int position) {
        PlayScreen playScreen = listPlayScreen.get(position);
        if(playScreen==null){
            return;
        }
        holder.imgLv.setImageResource(playScreen.getLvId());
        holder.tvTitle.setText(playScreen.getTitle());
        holder.tvPoint.setText(playScreen.getPoint());
        holder.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickPlayLevelListener.onClickPlayLevel(holder.imgLv,playScreen);

            }
        });
    }



    @Override
    public int getItemCount() {
        if(listPlayScreen!=null){
            return listPlayScreen.size();
        }
        return 0;
    }

    public class PlayViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgLv;
        private TextView tvTitle;
        private TextView tvPoint;
        private ImageView imgPlay;

        public PlayViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLv =itemView.findViewById(R.id.imgLv);
            tvTitle =itemView.findViewById(R.id.tvTitle);
            tvPoint =itemView.findViewById(R.id.tvPoint);
            imgPlay =itemView.findViewById(R.id.imgPlay);
        }
    }
}
