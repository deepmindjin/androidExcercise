package com.hongseokandrewjang.android.basicwidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // 결과값이 출력되는 텍스트뷰
    TextView tv;
    // 라디오 그룹
    RadioGroup rg;
    // 체크박스
    CheckBox cbDog;
    CheckBox cbChicken;
    CheckBox cbPig;
    // 스위치
    Switch sw;
    // 토글
    ToggleButton tg;
    // 프로그레스 바 (동글동글 돌아가는거)
    ProgressBar pb;
    // Seek Bar
    SeekBar sb;
    TextView sb_tv;
    // Rating Bar
    RatingBar rb;
    TextView rb_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.resultValue);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        sw = (Switch)findViewById(R.id.onOffSwitch);
        tg = (ToggleButton)findViewById(R.id.toggleButton);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        sb = (SeekBar)findViewById(R.id.seekBar);
        sb_tv = (TextView)findViewById(R.id.percentage);
        rb = (RatingBar)findViewById(R.id.ratingBar);
        rb_tv = (TextView)findViewById(R.id.ratingBarPercent);


        // 라디오 그룹
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // 위에서 int로 들어오는 checkedId 는 현재 체크된 라디오 버튼이다.
                // 현재 체크된 라디오 버튼 아이디를 int가 아닌 직접 가져오는 방법!
//                int checked = rg.getCheckedRadioButtonId();

                // 인텐트를 여기서 해줘야지 switch에서 하면 안됨
                // switch문은 case를 다 포함 한것을 하나로봐서 안됨
               Intent intent = null;
                switch (checkedId) {
                    case R.id.radioApple:
                        tv.setText("Apple이 선택됨");
                        intent = new Intent(MainActivity.this, DateActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.radioBanana:
                        tv.setText("Banana가 선택됨");
                        intent = new Intent(MainActivity.this, TabActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.radioOrange:
                        tv.setText("Orange가 선택됨");
                        intent = new Intent(MainActivity.this, SpinnerActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        // 체크박스
        cbChicken = (CheckBox) findViewById(R.id.checkBoxChicken);
        cbDog = (CheckBox) findViewById(R.id.checkBoxDog);
        cbPig = (CheckBox) findViewById(R.id.checkBoxPig);

        cbChicken.setOnCheckedChangeListener(checkedChangeListener);
        cbDog.setOnCheckedChangeListener(checkedChangeListener);
        cbPig.setOnCheckedChangeListener(checkedChangeListener);


        // 스위치
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    tv.setText("스위치가 on 되었습니다.");
                    pb.setVisibility(View.VISIBLE);
                }else{
                    tv.setText("스위치가 off 되었습니다.");
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 토글
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(tg.isChecked()){
                    Toast.makeText(MainActivity.this,"Toggle이 활성화 되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SeekBar
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sb_tv.setText(progress+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,seekBar.getProgress()+"위치에서 터치가 시작됨", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,seekBar.getProgress()+"위치에서 터치가 종료됨", Toast.LENGTH_SHORT).show();
            }
        });

        // Rating Bar
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rb_tv.setText(v+" / 5.0");
            }
        });

    }


        // 컴파운드 계열 (체크박스, 토글, 스위치...등) 버튼에서 사용되는 체크변화를 감지하는 리스너
         CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 체크박스는 switch로 하기 애매해서 if로 다 한다

                StringBuilder sb = new StringBuilder();

                if(cbChicken.isChecked()){
                    sb.append("Chicken ");
                }
                if(cbDog.isChecked()){
                    sb.append("Dog ");
                }
                if(cbPig.isChecked()){
                    sb.append("Pig ");
                }
                tv.setText(sb.toString());

                if(cbChicken.isChecked()&&cbDog.isChecked()&&cbPig.isChecked()){
                    Intent intent = new Intent(MainActivity.this, TextActivity.class);
                    startActivity(intent);
                }
            }
        };

}
