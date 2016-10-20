package com.hongseokandrewjang.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean running = true;
    public int deviceWidth;
    public int deviceHeight;

    CustomView customView;
    FrameLayout playground;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics =getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
                new MakeDrop(customView).start();
            }
        });
        btnStop = (Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
        customView = new CustomView(this);
        playground = (FrameLayout)findViewById(R.id.playground);
        playground.addView(customView);
    }

    class CustomView extends View{
        Paint paint = new Paint();
        // Thread safe List
        CopyOnWriteArrayList<RainDrop> rainDrops = new CopyOnWriteArrayList<>();

        public CustomView(Context context) {
            super(context);
        }

        public void add(RainDrop rainDrop){
            rainDrops.add(rainDrop);
        }

        public void remove(RainDrop rainDrop){
            rainDrops.remove(rainDrop);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLUE);

            for (RainDrop rainDrop : rainDrops) {
                // 하나씩 꺼내서 circle을 그려준다
                canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.size, paint);
            }
        }
    }

    // 빗방울 1개
    class RainDrop implements Runnable{
       CustomView customView;
        int speedLimit;
        int sizeLimit;
        int x;
        int y;
        int size;
        int speed;

        public RainDrop(CustomView customView){
            this.customView = customView;
            Random random = new Random();
            x = random.nextInt(deviceWidth);
            y = 0; // 고정
            sizeLimit = deviceWidth/20; // 최대 크기가 화면 가로의 1/20보다 작아야 한다
            size = random.nextInt(sizeLimit);
            speedLimit = deviceHeight/30;
            speed = random.nextInt(speedLimit)+1;
            customView.add(this);
        }

        @Override
        public void run() {
            while(true) {
                while (y <= deviceHeight) { // 화면 밖으로 벗어나면 while문을 빠져나가서 Thread가 종료된다
                    if (running) {
                        y = y + speed;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                customView.remove(this);
            }
        }
    }

    // 화면을 그려주는 thread
    class CallDraw implements Runnable{
        CustomView customView;
        int interval;

        public CallDraw(CustomView customView, int interval){
            this.customView = customView;
            this.interval = interval;
        }

        @Override
        public void run() {
            // interval에 입력된 값만큼 쉰 후에 화면을 반복해서 그려준다
            while(running){
                customView.postInvalidate();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 빗방울을 만들고 화면을 그려주는 Thread를 동작시키는 SubThread
    class MakeDrop extends Thread{
        CustomView customView;

        public MakeDrop(CustomView customView){
            this.customView = customView;
        }

        public void run(){
            // 화면을 그리는 Thread 생성 후 동작
            CallDraw cd = new CallDraw(customView,10);
            new Thread(cd).start();

            while(running) {
                RainDrop rainDrop = new RainDrop(customView);
                new Thread(rainDrop).start();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

