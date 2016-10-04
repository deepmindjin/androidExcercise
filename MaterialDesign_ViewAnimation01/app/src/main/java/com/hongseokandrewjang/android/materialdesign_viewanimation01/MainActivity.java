package com.hongseokandrewjang.android.materialdesign_viewanimation01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAlpha;
    Button btnRotate;
    Button btnTranslate;
    Button btnScale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlpha = (Button)findViewById(R.id.button);
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 미리 정의된 애니메이션 xml을 로드한다
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                // 2. 애니메이션을 뷰에 적용하고 실행한다
                btnAlpha.startAnimation(animation);
            }


        });

        btnRotate = (Button)findViewById(R.id.rotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
                btnRotate.startAnimation(animation);
            }


        });

        btnTranslate = (Button)findViewById(R.id.translate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
                btnTranslate.startAnimation(animation);
            }


        });

        btnScale = (Button)findViewById(R.id.scale);
        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);
                btnScale.startAnimation(animation);
            }


        });


    }
}

