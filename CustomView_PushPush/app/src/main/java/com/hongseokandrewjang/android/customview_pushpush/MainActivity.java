package com.hongseokandrewjang.android.customview_pushpush;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static int GROUND_LIMIT = 20;
    private int groundSize = 0;
    public int unit = 0;

    private int playerX = 0;
    private int playerY = 0;

    FrameLayout playGround;
    Button btnUp;
    Button btnLeft;
    Button btnRight;
    Button btnDown;
    CustomView cv;

    int map[][] =  {
            {9,1,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,1,0,1},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,1},
            {0,0,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,1,0,1,1,1,1,1,0,1,1,1,0,1},
            {0,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,0,1,0,1},
            {1,0,1,1,1,1,1,1,1,1,0,1,9,0,0,1,0,1,0,1},
            {1,0,1,0,0,0,0,0,0,0,0,1,1,1,0,1,0,1,0,0},
            {1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,1,0},
            {1,0,1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,0},
            {1,0,1,0,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0},
            {0,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0},
            {0,1,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1,0,1,0},
            {0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,1,0,1,1},
            {1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,0,0},
            {0,0,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,1,1,0},
            {0,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,0},
            {0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dp =getResources().getDisplayMetrics();
        groundSize = dp.widthPixels;
        unit = groundSize/GROUND_LIMIT;
        playerX = 0;
        playerY = 0;

        btnUp = (Button)findViewById(R.id.btnUp);
        btnDown = (Button)findViewById(R.id.btnDown);
        btnLeft = (Button)findViewById(R.id.btnLeft);
        btnRight = (Button)findViewById(R.id.btnRight);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
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
        }
        cv.postInvalidate();
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

        // 장애물 체크
        if(direction.equals("y")){
            int temp_y = playerY+nextValue;
            if ((map[temp_y][playerX]!=0)){
                if ((temp_y+nextValue)<0 || (temp_y+nextValue)>=GROUND_LIMIT||map[temp_y][playerX]==4||map[temp_y+nextValue][playerX]==4){
                    return 0;
                }else{
                    if (map[temp_y+nextValue][playerX]==1){
                        map[temp_y+nextValue][playerX] =  map[temp_y][playerX]+1;
                        map[temp_y][playerX] = 0;
                    }else{
                        map[temp_y+nextValue][playerX] =  map[temp_y][playerX];
                        map[temp_y][playerX] = 0;
                    }
                }
            }
        }else if(direction.equals("x")){
            int temp_x = playerX+nextValue;
            if (map[playerY][temp_x]!=0){
                if ((temp_x+nextValue)<0 || (temp_x+nextValue)>=GROUND_LIMIT||map[playerY][temp_x]==4||map[playerY][temp_x+nextValue]==4){
                    return 0;
                }else{
                    if(map[playerY][temp_x+nextValue]==1) {
                        map[playerY][temp_x + nextValue] = map[playerY][temp_x] + 1;
                        map[playerY][temp_x] = 0;
                    }else{
                        map[playerY][temp_x + nextValue] = map[playerY][temp_x];
                        map[playerY][temp_x] = 0;
                    }
                    return nextValue;

                }
            }
        }

//        // 포탈
//        if(direction.equals("y")){
//            if (map[playerY+nextValue][playerX]==3){
//                if (playerX==17){
//                    playerX = 19;
//                    playerY = 0;
//                }else{
//                    playerX = 16;
//                    playerY = 19;
//                }
//                return 0;
//            }
//        }else if(direction.equals("x")){
//            if (map[playerY][playerX+nextValue]==3){
//                if (playerX==17){
//                    playerX = 19;
//                    playerY = 0;
//                }else{
//                    playerX = 16;
//                    playerY = 18;
//                }
//                return 0;
//            }
//        }
        return nextValue;
    }


    class CustomView extends View {
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.DKGRAY);
        }
        protected void onDraw(Canvas canvas) {
            // 운동장 배경 그리기
            paint.setColor(Color.CYAN);
            canvas.drawRect(0,0, groundSize, groundSize,paint);
            // map에 세팅된 장애물 그리기
            paint.setColor(Color.BLACK);
            for(int i=0;i<GROUND_LIMIT;i++){
                for(int j=0;j<GROUND_LIMIT;j++){
                    if (map[j][i] == 1){
                        canvas.drawRect(i*unit,j*unit,i*unit + unit, j*unit + unit,paint);
                    }else if(map[j][i] == 2){
                        Paint paintForPortal = new Paint();
                        paintForPortal.setColor(Color.MAGENTA);
                        canvas.drawRect(i*unit,j*unit,i*unit + unit, j*unit + unit,paintForPortal);
                    }else if(map[j][i] == 3){
                        Paint paintForPortal = new Paint();
                        paintForPortal.setColor(Color.GREEN);
                        canvas.drawRect(i*unit,j*unit,i*unit + unit, j*unit + unit,paintForPortal);
                    }else if(map[j][i] == 4){
                        Paint paintForPortal = new Paint();
                        paintForPortal.setColor(Color.DKGRAY);
                        canvas.drawRect(i*unit,j*unit,i*unit + unit, j*unit + unit,paintForPortal);
                    }else if(map[j][i] == 9) {
                        Paint paintSpecial = new Paint();
                        paintSpecial.setColor(Color.RED);
                        canvas.drawRect(i * unit, j * unit, i * unit + unit, j * unit + unit, paintSpecial);
                    }
                }
            }
            // 플레이어 그리기
            paint.setColor(Color.BLUE);
            canvas.drawRect(playerX*unit,playerY*unit,playerX*unit+unit,playerY*unit+unit,paint);

        }
//        protected void onDraw(Canvas canvas) {
//            for(int i=0;i<10;i++){
//                for(int j=0;j<10;j++){
//                    canvas.drawRect(i,j,x+unit,y+unit,null);
//                }
//            }
//        }
    }

}


