package com.hongseokandrewjang.android.shootingboy;

import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "TAG";
    public static ImageView player;
    public static ImageView balloon;
    public static int playerPosition;
    public static int balloonPosition;
    public static int groundWidth;
    public static int groundHeight;

    public static String BULLET_REQUEST_CODE = "BULLET";
    public static String BALLOON_REQUEST_CODE = "BALLOON";
    public static String CRASH_REQUEST_CODE = "CRASH";

    public static final String PENDING_RESULT = "pending_result";
    public static final String RESULT = "result";
    public static final int RESULT_CODE = "ok".hashCode();

    LinearLayout playGround;
    int playTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageView)findViewById(R.id.player);
        balloon = (ImageView)findViewById(R.id.balloon);
        playGround = (LinearLayout)findViewById(R.id.playGround);
        playTime = 60;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startGame(View v){
        setGroundSize();

        // Shooting Bullet 1shoot / 1 sec
        PendingIntent pendingBullet = createPendingResult(BULLET_REQUEST_CODE, new Intent(), 0);
        Intent bulletIntent = new Intent(this, ShootingBulltet.class);
        bulletIntent.putExtra(PENDING_RESULT, pendingBullet);
        startService(intent);

        // Moving Balloon
        PendingIntent pendingBalloon = createPendingResult(BALLOON_REQUEST_CODE, new Intent(), 1);
        Intent balloonIntent = new Intent(this, MovingBalloon.class);
        balloonIntent.putExtra(PENDING_RESULT, pendingBalloon);
        startService(intent);

        // Checking Crash
        PendingIntent pendingCrash = createPendingResult(CRASH_REQUEST_CODE, new Intent(), 2);
        Intent crashIntent = new Intent(this, ChekckingCrash.class);
        crashIntent.putExtra(PENDING_RESULT, pendingCrash);
        startService(intent);



        // Moving Balloon 1move / 1 sec
        for(int i=0;i<3;i++){
            moveBalloon();
            Log.i(TAG,"============= moveBalloon"+i);
        }
    }
    protected void onActivityResult(int req, int res, Intent data) {
        if (req == BULLET_REQUEST_CODEREQUEST_CODE && res == RESULT_CODE) {
            int result = data.getExtras().getInt("key1");
            spinBar(bar, result);
        }else if (req == BALLOON_REQUEST_CODEREQUEST_CODE && res == RESULT_CODE) {
            moveBalloon();
        }else if (req == CRASH_REQUEST_CODE && res == RESULT_CODE) {
            moveBalloon();
        }
        super.onActivityResult(req, res, data);
    }

    public  void moveLeft(View v) {
        setGroundSize();

        playerPosition = playerPosition - 50;
        if (!(playerPosition < -groundWidth/2)) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(player, "translationX", playerPosition);
            animator.start();
            Log.i(TAG, "============= Player Position After Move: "+playerPosition);
        } else {
            playerPosition = playerPosition + 50;
            Toast.makeText(this, "왼쪽의 끝입니다", Toast.LENGTH_SHORT).show();
        }
    }
    public void moveRight(View v){
        setGroundSize();

        playerPosition = playerPosition + 50;
        if(!(playerPosition > groundWidth/2)) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(player, "translationX", playerPosition);
            animator.start();
            Log.i(TAG, "============= Player Position After Move: "+playerPosition);
        }else {
            playerPosition = playerPosition - 50;
            Toast.makeText(this,"오른쪽의 끝입니다",Toast.LENGTH_SHORT).show();
        }
    }

    public void moveBalloon(){
        int randomMove;
        double randomNum = Math.random()*3;
            Log.i(TAG, "============= randomNum : " + randomNum);
        randomMove = 50*((int)randomNum - 1);
            Log.i(TAG, "============= randomMove : " + randomMove);
        balloonPosition = balloonPosition + randomMove;
            Log.i(TAG, "============= balloon Position : "+balloonPosition);
        if((balloonPosition < groundWidth/2) && (balloonPosition > -groundWidth/2)) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(balloon, "translationX", balloonPosition);
            animator.start();
            Log.i(TAG, "============= balloon Position After Move: "+balloonPosition);
        }else{
            balloonPosition = balloonPosition - randomMove;
        }
    }
    public void setGroundSize(){
        if(groundWidth == 0){
            groundWidth = playGround.getWidth();
            groundHeight = playGround.getHeight();
        }
    }
}

