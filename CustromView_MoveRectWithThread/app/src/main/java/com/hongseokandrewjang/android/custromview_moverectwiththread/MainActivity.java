package com.hongseokandrewjang.android.custromview_moverectwiththread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout ground;
    Button btnAnimate;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cv = new CustomView(this);
        btnAnimate = (Button)findViewById(R.id.btnAnimate);
        ground = (FrameLayout)findViewById(R.id.ground);
        ground.addView(cv);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomThread thread = new CustomThread(cv);
                thread.start();
            }
        });
    }
}

class CustomView extends View {
    private int x = 0;
    private int y = 0;
    private final static int WIDTH = 100;
    private final static int HEIGHT = 100;
    Paint paint = new Paint();

    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.DKGRAY);
    }

    public void reset(){
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,x+HEIGHT,y+HEIGHT,paint);
    }

    public void moveRect(int offset){
        x = x+offset;
        y = y+offset;
    }
}

class CustomThread extends Thread{
    CustomView cv;
    private static final int OFFSET = 2;
    public CustomThread(CustomView cv){
        this.cv = cv;
    }
    @Override
    public void run(){
        int limit = 0;
        while(limit < 1000){
            cv.moveRect(OFFSET);
            cv.postInvalidate();
            limit++;
            try {
                Thread.sleep(10);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}