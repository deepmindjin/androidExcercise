package com.hongseokandrewjang.android.servicebasic01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "============ Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void serviceStart(View v){
        // 1. 서비스 실행
        Intent intent = new Intent(this,MainService.class);
        startService(intent);


            // 이렇게 하면 Main Activity안에 있는 이 for문 먼저 돌아가고
            // 그다음 service에 있는게 돌아간다
            // 즉, 하나의 thread안에서 돌아간다
//        for(int i=0;i<100000;i++){
//            Log.i(TAG,"========Activity i");
//        }
    }

    public void serviceStop(View v){
        // 2, 서비스 중지
        Intent serInt = new Intent(this, MainService.class);
        stopService(serInt);
    }

    public void startIntentService(View v){
        Intent intent = new Intent(this,SubService.class);
        startService(intent);
    }

    public void stopIntentService(View v){
        Intent intent = new Intent(this,SubService.class);
        stopService(intent);
    }
}
