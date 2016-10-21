package com.hongseokandrewjang.android.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {
    int deviceWidth = 0;
    int deviceHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        deviceHeight = dp.heightPixels;
        deviceWidth = dp.widthPixels;

        CustomSurfaceView surfaceView = new CustomSurfaceView(this);
        setContentView(surfaceView);
    }

    public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        private SurfaceThread thread;

        public CustomSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            thread = new SurfaceThread(getHolder()); //
//            thread.setDaemon(true);
            // 클릭 이벤트를 만들어 줄 수 있다.
//            setFocusable(true);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        // 뷰가 화면에 보여지는 시점
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            thread.start();
            thread.running = true;
        }

        // 뷰에 변경사항이 생겼다(사이즈 변경 등...)
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        // 화면에서 뷰가 보이지 않는 시점
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            thread.interrupt();
            while (retry) {
                try {
                    thread.join(); // 서브스레드를 메인과 동기화 시켜준다
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 무한반복하면서 위에서 정의한 surface 뷰에 그림을 그려주는 역할을 한다
    public class SurfaceThread extends Thread {

        boolean running = false;
        private SurfaceHolder surfaceHolder;
        Paint paint = new Paint();
        int x = 0;
        int y = 0;
        Canvas canvas;

        public SurfaceThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        @Override
        public void run() {
            // 무한 반복하면서 그림을 그려준다
            try {
                while (running) {
                    // lock을 한다는건 내가 쓰는 holder를 다른 것들이 못 쓰게 한다
                    // 그림을 그릴 캔버스를 가져오고
                    canvas = surfaceHolder.lockCanvas();
                    // 메모리에 그림을 그린다
                    synchronized (surfaceHolder) {
                        paint.setColor(Color.BLACK);
                        canvas.drawRect(x - 1, y - 1, x + 51, y + 51, paint);
                        paint.setColor(Color.BLUE);
                        canvas.drawRect(x, y, x + 50, y + 50, paint);
                    }
                    x++;
                    y++;

                    if ((x > deviceWidth) || (x < 0)) {
                        x = 0;
                        y = 0;
                    }
                }
            } catch (Exception e) {
            } finally {
                if (running)
                // 메모리에 그린 그림을 실제 디스플레이에 그려주게 된다.
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
