package com.hongseokandrewjang.android.materialdesign_elevation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
    // API levle 21 이상에서 Material design 설정
    1. res/values/style.xml 의 theme을 android:Theme.Material로 변경
    2. AndroidManifest.xml 의 application 의 theme 속성을 변경된 theme으로 지정(Default로 되어있음)
    3. Activity의 상속 클래스를 AppCompatActivity에서 Activity로 변경


    // API level 21 미만에서 설정 안됨
    1. 상속 받는 Activity를 원래대로 AppCompatActivity로 변경
    2. Style의 AppTheme을 Theme.AppCompat 으로 변경

 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
