package com.hongseokandrewjang.android.firebase_database01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-11-01.
 */

public class CustomAdapter<T> extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    ArrayList<T> datas = new ArrayList<>();
    int itemLayout;
    Context mContext;

    public CustomAdapter(ArrayList<T> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, final int position) {
        T data = datas.get(position);

        if(data instanceof ChickenStore){
            Glide.with(mContext).load(((ChickenStore) data).getLOGO()).into(holder.imgInDetail);

            holder.tvName_in_detail.setText(((ChickenStore) data).getNAME()+" "+((ChickenStore) data).getBRANCH());
            holder.tvPrice_in_detail.setText(((ChickenStore) data).getDELIVERY_FEE()+"");
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("position", position);
                    mContext.startActivity(intent);
                }
            });
        }else if(data instanceof MENU){

            Glide.with(mContext).load(((MENU) data).getMENU_IMAGE()).into(holder.imgInDetail);
            holder.tvName_in_detail.setText(((MENU) data).getMENU_NAME());
            holder.tvPrice_in_detail.setText(((MENU) data).getMENU_PRICE()+"");
        }
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgInDetail;
        TextView tvName_in_detail;
        TextView tvPrice_in_detail;
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.card_view_in_detail);
            imgInDetail = (ImageView)itemView.findViewById(R.id.imgView_in_detail);
            tvName_in_detail = (TextView)itemView.findViewById(R.id.tvName_in_detail);
            tvPrice_in_detail = (TextView)itemView.findViewById(R.id.tvPrice_in_detail);
        }
    }
}
