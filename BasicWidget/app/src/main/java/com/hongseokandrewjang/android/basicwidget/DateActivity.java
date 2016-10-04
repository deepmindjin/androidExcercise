package com.hongseokandrewjang.android.basicwidget;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class DateActivity extends AppCompatActivity {

    Chronometer timer;

    Button start;
    Button stop;
    Button pause;
    // 현재 일시정지 버튼이 눌렸는지 체크
    Boolean pauseFlag = false;

    long pauseTime = 0;
    long pastTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        timer = (Chronometer)findViewById(R.id.timer);
        pause = (Button)findViewById(R.id.btnPause);
        start = (Button)findViewById(R.id.btnStart);
        stop = (Button)findViewById(R.id.btnStop);

        start.setOnClickListener(onClickListener);
        pause.setOnClickListener(onClickListener);
        stop.setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btnStart:
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    break;
                case R.id.btnStop:
                    timer.stop();
                    break;
                case R.id.btnPause:
                    if(pauseFlag){
                        pastTime = 0;
                        pauseFlag = !pauseFlag;
                        timer.stop();
                        pauseTime =  SystemClock.elapsedRealtime();
                    }else{
                        pauseFlag = !pauseFlag;
                        pastTime = SystemClock.elapsedRealtime() - pauseTime;
                        timer.setBase(timer.getBase() + pastTime);
                        timer.start();
                        pauseTime = 0;
                    }
            }
        }
    };
}
