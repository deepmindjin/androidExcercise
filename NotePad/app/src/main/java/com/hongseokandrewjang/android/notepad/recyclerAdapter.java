package com.hongseokandrewjang.android.notepad;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder>{

    ArrayList<BbsData> bbsDataBase;
    int itemLayout;
    MainActivity context;

    public recyclerAdapter(ArrayList<BbsData> data, int itemLayout, MainActivity context){
        this.bbsDataBase = data;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final recyclerAdapter.ViewHolder holder, final int position) {
        final BbsData data = bbsDataBase.get(position);
        holder.title.setText(data.title);
        holder.itemView.setTag(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.goToDetail(data);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("게시글 삭제")        // 제목 설정
                        .setMessage("게시글을 삭제하시겠습니까?")        // 메세지 설정
                        .setCancelable(true)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                            // 확인 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                MainActivity.BbsDataBase.remove(position);
                                recyclerAdapter recycleradapter = new recyclerAdapter(MainActivity.BbsDataBase, R.layout.content_item,MainActivity.this);
                                MainActivity.setAdapter(recycleradapter);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();    // 알림창 띄우기
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return bbsDataBase.size();
    }

    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.titleTextView);
        }
    }
}
