package com.hongseokandrewjang.android.firebase_auth;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HongSeokAndrewJang on 2016-10-31.
 */

public class CustomAdapter extends BaseAdapter{

    ArrayList<User> users = new ArrayList<>();
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<User> users, LayoutInflater inflater) {
        this.users = users;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_layout,null);
        }

        TextView tvName = (TextView)convertView.findViewById(R.id.tvUserName);
        TextView tvEmail = (TextView)convertView.findViewById(R.id.tvUserEmail);

        User user = users.get(position);
        String password = user.userPassword;
        String email = user.userEmail;
        tvEmail.setText(email);
        tvName.setText(password);

        return convertView;
    }
}
