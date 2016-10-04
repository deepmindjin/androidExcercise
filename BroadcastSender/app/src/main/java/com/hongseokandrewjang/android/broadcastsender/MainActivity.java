package com.hongseokandrewjang.android.broadcastsender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String BROADCAST_ACTION = "com.hongseokandrewjang.android.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBroadCast(View v){
        Intent intent = new Intent(BROADCAST_ACTION);
        intent.putExtra("msg","Hello APP SENDER!");
        sendBroadcast(intent);
    }
}
