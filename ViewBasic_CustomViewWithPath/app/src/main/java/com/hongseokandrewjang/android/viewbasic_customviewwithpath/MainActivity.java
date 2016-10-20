package com.hongseokandrewjang.android.viewbasic_customviewwithpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    CustomView cv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cv = new CustomView(this);

        final FrameLayout frameLayout = (FrameLayout)findViewById(R.id.ground);
        frameLayout.addView(cv);
        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.reset();
            }
        });
    }
}


class CustomView extends View {
    Paint paint = new Paint();
    Path path =  new Path();

    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    public void reset(){
        path = new Path();
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y); // 시작점을 표시
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
        }
        invalidate();
        return true;
    }



}
