package com.hongseokandrewjang.android.servicebasic01;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class SubService extends IntentService {

    public static  final String TAG = "SubService";

    public SubService() {
        super("SubService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int tiktok = 0;
        for(int i = 0;i<10;i++){

        }

     Log.i(TAG,"================================== on Handle Intent");
        for(int i=0;i<1000;i++){
            Log.i(TAG,"=====start Intent Service in Main "+i);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
