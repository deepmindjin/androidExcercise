package com.hongseokandrewjang.android.fragmentbasic03;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 장홍석 on 2016-10-05.
 */

public class ListviewAdapter extends BaseAdapter{

    MainActivity main;

    public ListviewAdapter(MainActivity main){
        this.main = main;
    }

    @Override
    public int getCount() {
        return main.datas.size();
    }

    @Override
    public Object getItem(int position) {
        return main.datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView= main.getLayoutInflater().inflate(R.layout.list_item,null);
        }

        TextView artist = (TextView)convertView.findViewById(R.id.item_artist);
        TextView title = (TextView)convertView.findViewById(R.id.item_title);
        artist.setText(main.datas.get(position).artist);
        title.setText(main.datas.get(position).title);

        return convertView;
    }
}
