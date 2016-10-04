package com.hongseokandrewjang.android.basiclist1;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;


    public RecyclerCardAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context){
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
    }
    // View 를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    // 일반 list adapter 의 getView 를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        RecyclerData data = datas.get(position);
        holder.image.setBackgroundResource(data.image);
        holder.title.setText(data.title);
        holder.artist.setText(data.artist);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("position",position);
//                intent.putExtra("image",data.image);
//                intent.putExtra("title",data.title);
//                intent.putExtra("artist",data.artist);
                context.startActivity(intent);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"이미지가 클릭됨",Toast.LENGTH_LONG).show();
            }
        });

        setAnimation(holder.cardView,position);

        // Holder는 태그를 기반으로 동작
        holder.itemView.setTag(data);
    }

    int lastPosition = -1;
    // *---------- Animation을 우리가설정해준다 -----------*
    public void setAnimation(View view,int position){
        if(position>lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }
    // *---------- Animation을 우리가설정해준다 -----------*
    @Override
    public int getItemCount() {
        return datas.size();
    }

    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView artist;
        TextView title;
        ImageView image;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.recyclerCardItem);
            artist = (TextView)itemView.findViewById(R.id.artist);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}
