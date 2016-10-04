package com.hongseokandrewjang.android.materialdesign_propertyanimation01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

/*
    ObjectAnimator 사용법
    1. 애니메이터를 정의한다
    ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", 속성값(숫자));
                                                            ↑ 개체의 속성명
 */





public class MainActivity extends AppCompatActivity {

    ImageButton player;
    RelativeLayout ground;
    int x = 0;
    int y = 0;
    int gx = 0;
    int gy = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageButton)findViewById(R.id.player);
        ground = (RelativeLayout)findViewById(R.id.ground);

    }

    // Animation을 할 때는 처음 시작한 좌표가 0,0임
    private void setGroundSize(){
        if(gx == 0) {
            gx = ground.getWidth();
            gy = ground.getHeight();
        }
    }

    public void up(View v){
        setGroundSize();
        y = y - 50;

        if( -(gy/2) <= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.start();
        } else{
            y = y + 50;
            Toast.makeText(this,"Out Of Bound",Toast.LENGTH_SHORT).show();
        }
    }

    public void down(View v){
        setGroundSize();
        y = y + 50;
        if ((gy/2)>=y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.start();
        }else{
            y = y - 20;
            Toast.makeText(this,"Out Of Bound",Toast.LENGTH_SHORT).show();
        }
    }

    public void left(View v){
        setGroundSize();
        x = x - 50;
        if(-(gx/2)<=x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.start();
        }else{
            x = x + 50;
            Toast.makeText(this,"Out Of Bound",Toast.LENGTH_SHORT).show();
        }
    }

    public void right(View v){
        setGroundSize();
        x = x + 50;
        if(gx/2>=x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.start();
        }else{
            x = x - 50;
            Toast.makeText(this,"Out Of Bound",Toast.LENGTH_SHORT).show();
        }
    }

    public void showMessage(View v){
        Toast.makeText(this,"I'm Here!!!", Toast.LENGTH_SHORT).show();
    }

    float scale= 1;
    public void smaller(View v){
        scale = scale/2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player,"scaleX",scale); // 배수
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player,"scaleY",scale);

        // 여러개의 애니메이션 동시에 사용하기
        // 1. AnimatorSet을 초기화힌다
        AnimatorSet aniSet = new AnimatorSet();
        // 2. playTogether에 담아준다
        aniSet.playTogether(ani1,ani2);
        // 3. 애니메이션을 실행한다
        aniSet.start();
    }

    public void bigger(View v){
        scale = scale*2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player,"scaleX",scale); // 배수
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player,"scaleY",scale);

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(ani1,ani2);
        aniSet.start();
    }

    int r = 0;
    public void rotate(View v){
        r = r + 90;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "rotation", r);
        ani.start();
    }
}

