package com.hongseokandrewjang.android.medialibrary;

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

import java.util.ArrayList;

/**
 * Created by 장홍석 on 2016-10-04.
 */

public class cardAdapter extends RecyclerView.Adapter<cardAdapter.ViewHolder>{


    ArrayList<cardData> datas;
    int itemLayout;
    Context context;


    public cardAdapter(ArrayList<cardData> datas, int itemLayout, Context context){
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        cardData data = datas.get(position);
        holder.image.setImageBitmap(data.image);
        holder.title.setText(data.title);
        holder.artist.setText(data.artist);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, cardDetail.class);
                intent.putExtra("POSITION",position);
                context.startActivity(intent);
            }
        });
        setAnimation(holder.cardView,position);
        holder.itemView.setTag(data);
    }

    int lastPosition = -1;
    public void setAnimation(View view,int position){
        if(position>lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        CardView cardView;
        TextView artist;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardViewItem);
            title = (TextView)itemView.findViewById(R.id.detailTitle);
            image = (ImageView)itemView.findViewById(R.id.detailImage);
            artist = (TextView)itemView.findViewById(R.id.detailArtist);
        }
    }
}
