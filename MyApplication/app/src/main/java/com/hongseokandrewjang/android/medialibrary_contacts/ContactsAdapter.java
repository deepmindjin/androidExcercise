package com.hongseokandrewjang.android.medialibrary_contacts;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 장홍석 on 2016-10-05.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    ArrayList<ContactsData> datas;
    int itemLayout;
    Context context;


    public ContactsAdapter(ArrayList<ContactsData> datas, int itemLayout, Context context){
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
        ContactsData data = datas.get(position);
        holder.nameSpace.setText(data.name);
        holder.numberSpace.setText(data.number);
        setAnimation(holder.contactCard,position);
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
        TextView nameSpace;
        TextView numberSpace;
        CardView contactCard;

        public ViewHolder(View itemView) {
            super(itemView);
            contactCard = (CardView)itemView.findViewById(R.id.contactCard);
            nameSpace = (TextView)itemView.findViewById(R.id.nameSpace);
            numberSpace = (TextView)itemView.findViewById(R.id.numberSpace);
        }
    }
}
