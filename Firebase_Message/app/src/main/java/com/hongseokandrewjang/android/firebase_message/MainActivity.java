package com.hongseokandrewjang.android.firebase_message;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final int GET_NOTIFICATION = 0;

    Button btnGenerateToken;
    public static TextView tvResult;
    public static Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView)findViewById(R.id.tvResult);

        btnGenerateToken = (Button)findViewById(R.id.btnGenerateToken);
        btnGenerateToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFirebaseInstanceIDService IDservice = new MyFirebaseInstanceIDService();
                IDservice.onTokenRefresh();
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("news");
    }

    public static void setTV(final String text) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tvResult.setText(text);
            }
        };
        mHandler.post(runnable);
    }
}
