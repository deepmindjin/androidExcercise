package com.hongseokandrewjang.android.threadbasic_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnStart, btnCall, btnHandlerThread;
    SubThread subThread;
    LooperHandler handlerThread;
    Handler subHandler;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    int temp = msg.arg1;
                    textView.setText("input from SubThread : "+temp);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handlerThread = new LooperHandler(MainActivity.this,"looper Handler");
        handlerThread.start();

        subThread = new SubThread(handler);
        subHandler = handlerThread.looperHandler; // 서브스레드에 핸들러를 등록한다

        textView = (TextView)findViewById(R.id.showResult);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subThread.start();
            }
        });

//        btnCall = (Button)findViewById(R.id.btnCall);
//        btnCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                subThread.printLog();
//            }
//        });
//        btnHandlerThread = (Button)findViewById(R.id.btnHandlerThread);
//        btnHandlerThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handlerThread.looperHandler.sendEmptyMessage(LooperHandler.SHOW_PROGRESS);
//                handlerThread.start();
//                run();
//                handlerThread.hideProgress();
//                handlerThread.looperHandler.sendEmptyMessage(LooperHandler.HIDE_PROGRESS);
//            }
//        });
    }

    public void run(){
        try {
            int sum = 10;
            for (int i = 0; i < 20; i++) {
                sum += i;
                Thread.sleep(100);
            }
        }catch (Exception e){
        }
    }

    // LOOPER HANDLER CLASS
    class LooperHandler extends HandlerThread{
        public static final int SHOW_PROGRESS = 1;
        public static final int HIDE_PROGRESS = 2;
        Context context;
        Handler looperHandler;
        ProgressDialog progress;
        public LooperHandler(Context context, String name) {
            super(name);
            this.context = context;

        }

//        @Override
//        protected void onLooperPrepared() {
//            super.onLooperPrepared();
//
//            progress = new ProgressDialog(context);
//            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progress.setTitle("Progress Bar Title");
//            progress.setMessage("Message");
//            progress.setCancelable(false);
//            progress.show();

//            looperHandler = new Handler(){
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    switch (msg.what){
//                        case SHOW_PROGRESS:
//                            showProgress();
//                            break;
//                        case HIDE_PROGRESS:
//                            hideProgress();
//                            break;
//                    }
//                }
//            };
//        }

        public void showProgress(){
            progress.show();
        }

        public void hideProgress(){
            progress.dismiss();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            quit();
        }
    }


    // SUB THREAD CLASS
    class SubThread extends Thread{
        Handler mainHandler;
        SubThread(Handler handler){
            mainHandler = handler;
        }
        @Override
        public void run() {
            Message looperMessage = new Message();
            looperMessage.what = 10;
            int sum = 0;
            for(int i=0;i<5000;i++){
                sum = sum + i;
                looperMessage.arg1 = sum;
            }
            Message message = new Message();
            message.what = 1;
            message.arg1 = sum;
            mainHandler.sendMessage(message);
        }
    }
}
