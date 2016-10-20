package com.hongseokandrewjang.android.optimization_render;

import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Debug.startMethodTracing("traceForThread");

        print1000("MAIN");
        Thread anotherThread01 = new Thread(){
            public void run(){
                print1000("inThread1");
                // 다른 쓰레드에서 돌아가는 로직
            }
        };

        Thread anotherThread02 = new Thread(){
            public void run(){
                print1000("inThread2");
                // 다른 쓰레드에서 돌아가는 로직
            }
        };
        anotherThread01.start();
        anotherThread02.start();
    }

    public void print1000(String tag){
        for(int i=0;i<1000;i++){
            System.out.println("i = "+i+", tag = "+tag);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}
