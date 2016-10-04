package com.hongseokandrewjang.android.basiclist1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    ArrayList<RecyclerData> datas;
    int itemLayout;


    public RecyclerAdapter(ArrayList<RecyclerData> datas, int itemLayout){
        this.datas = datas;
        this.itemLayout = itemLayout;
    }
    // View 를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    // 일반 list adapter 의 getView 를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerData data = datas.get(position);
        holder.image.setImageResource(data.image);
        holder.title.setText(data.title);
        holder.artist.setText(data.artist);
        // Holder는 태그를 기반으로 동작
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView artist;
        TextView title;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            artist = (TextView)itemView.findViewById(R.id.artist);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.img);
        }
    }
}
