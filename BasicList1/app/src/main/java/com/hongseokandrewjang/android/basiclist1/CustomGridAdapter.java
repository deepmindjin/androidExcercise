package com.hongseokandrewjang.android.basiclist1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomGridAdapter extends BaseAdapter {

    // 기본 속성값 설정
    Context context;    // 1. Context
    ArrayList<Griditem> datas;    // 2. 데이터
    int griditem;       // 3. 레이아웃 아이템
    LayoutInflater inflater;// 4. 인플레이터

    public CustomGridAdapter(Context context, ArrayList datas, int griditem){
        this.context = context;
        this.datas = datas;
        this.griditem = griditem;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // 1. 뷰를 생성 / 있으면 그냥사용
        if(view == null){
            view = inflater.inflate(griditem,null);
        }

        // 2. 뷰에 있는 위젯아이디 가져와서 세팅
        TextView tv1 = (TextView)view.findViewById(R.id.hint1);
        TextView tv2 = (TextView)view.findViewById(R.id.hint2);

        // 이렇게 걍 title로 접근이 가능한 이유는 arrayList에서 <>로 제네릭 설정해서 그렇다
        tv1.setText(datas.get(i).title);
        tv2.setText(datas.get(i).num+"");

        return view;
    }
}
