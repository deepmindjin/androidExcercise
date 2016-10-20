package com.hongseokandrewjang.android.customview_rectai;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final static int GROUND_LIMIT = 10;
    private int groundSize = 0;
    public int unit = 0;

    private float playerX = 0;
    private float playerY = 0;
    private float enemyX = 0;
    private float enemyY = 0;
    private boolean gameStart = false;

    FrameLayout playGround;
    Button btnUp;
    Button btnLeft;
    Button btnRight;
    Button btnDown;
    Button btnStart;
    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dp =getResources().getDisplayMetrics();
        groundSize = dp.widthPixels;
        unit = groundSize/GROUND_LIMIT;
        playerX = 0;

        btnUp = (Button)findViewById(R.id.btnUp);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        playGround = (FrameLayout)findViewById(R.id.ground);
        cv = new CustomView(this);

        playGround.addView(cv);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnUp:    playerY = playerY + checkCollision("y",-1);break;
            case R.id.btnDown:  playerY = playerY + checkCollision("y",+1);break;
            case R.id.btnLeft:  playerX = playerX + checkCollision("x",-1);break;
            case R.id.btnRight: playerX = playerX + checkCollision("x",+1);break;
            case R.id.btnStart: start(); break;
        }
        cv.postInvalidate();
    }

    public void start(){
        gameStart = true;
        CustomThread thread = new CustomThread(cv);
        thread.start();
    }





    private int checkCollision(String direction, int nextValue){
        // 외곽선 체크
        if(direction.equals("y")){
            if ((playerY+nextValue)<0 || (playerY+nextValue)>=GROUND_LIMIT)
                return 0;
        }else{
            if ((playerX+nextValue)<0 || (playerX+nextValue)>=GROUND_LIMIT)
                return 0;
        }
        return nextValue;
    }


    class CustomView extends View {
        Paint paint = new Paint();

        public void drawEnemy(Canvas canvas){
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle(enemyX*unit,enemyY*unit,50,paint);
        }

        public void moveEnemy(){
            if (enemyX*unit>playerX*unit+unit/2){
                enemyX = (float)(enemyX - 0.001);
            }else if(enemyX*unit<playerX*unit+unit/2){
                enemyX = (float)(enemyX + 0.001);
            }

            if (enemyY*unit>playerY*unit+unit/2){
                enemyY = (float)(enemyY - 0.001);
            }else if(enemyY*unit<playerY*unit+unit/2){
                enemyY = (float)(enemyY + 0.001);
            }
        }


        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.DKGRAY);
        }
        protected void onDraw(Canvas canvas) {
            // 운동장 배경 그리기
            paint.setColor(Color.CYAN);
            canvas.drawRect(0,0, groundSize, groundSize,paint);
            // 플레이어 그리기
            paint.setColor(Color.BLUE);
            canvas.drawRect(playerX*unit,playerY*unit,playerX*unit+unit,playerY*unit+unit,paint);
            // 적 그리기
            if(gameStart){
                drawEnemy(canvas);
            }

        }
    }

    class Enemy extends Thread{
        int x = 0;
        int y = 0;
        int size = unit;
        CustomView cv;

        public Enemy(CustomView cv){
            this.cv = cv;
        }

        @Override
        public void run(){
            while(playerDown()){
                cv.moveEnemy();
                cv.postInvalidate();
                try {
                    Thread.sleep(1);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean playerDown(){
            float distance_x = playerX*unit - x;
            float distance_y = playerY*unit - y;
            boolean life = true;
            if ((enemyX*unit==playerX*unit+unit/2)&&(enemyY*unit==playerY*unit+unit/2)){
                life = false;
            }
            return life;
        }
    }
    }

    class CustomThread extends Thread{
        CustomView cv;
        public CustomThread(CustomView cv){
            this.cv = cv;
        }
        @Override
        public void run(){
            while(playerDown()){
                cv.moveEnemy();
                cv.postInvalidate();
                try {
                    Thread.sleep(1);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public boolean playerDown(){
            boolean life = true;
            if ((enemyX*unit==playerX*unit+unit/2)&&(enemyY*unit==playerY*unit+unit/2)){
                life = false;
            }
            return life;
        }
    }
}





