package com.hongseokandrewjang.android.basiclist1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandableAdapter extends BaseExpandableListAdapter{

    Context context;
    int groupLayout;
    int childLayout;
    ArrayList<ExpandData> datas;
    LayoutInflater inflater;

    public ExpandableAdapter(Context context, int groupLayout, int childLayout, ArrayList<ExpandData> datas) {
        this.context = context;
        this.childLayout = childLayout;
        this.groupLayout = groupLayout;
        this.datas = datas;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return datas.get(i).guNames.size();
    }

    @Override
    public Object getGroup(int i) {
        return datas;
    }

    @Override
    public Object getChild(int i, int i1) {
        return datas.get(i).guNames.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(groupLayout,viewGroup,false);
        }
        TextView tv = (TextView)view.findViewById(R.id.parentTV);
        tv.setText(datas.get(i).cityName);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, final ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(childLayout,viewGroup,false);
        }
        TextView tv = (TextView)view.findViewById(R.id.childTV);
        tv.setText(datas.get(i).guNames.get(i1));
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(,"asdfsadf",Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
