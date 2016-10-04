package com.hongseokandrewjang.android.basicwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {

    String days[] = {"월", "화", "수", "목", "금", "토", "일"};
    String months[] = {};
    Spinner sp1;
    Spinner sp2;
    TextView sp1_tv;
    TextView sp2_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        sp1 = (Spinner)findViewById(R.id.spinner);
        sp1_tv = (TextView)findViewById(R.id.spinnerText);

        // 첫번째 : Context
        // 두번째 : 줄당 레이아웃 ( 표시되는 각각의 객체가 어떤 layout을 가질지)
        // 세번째 : 데이터 배열
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(SpinnerActivity.this, android.R.layout.simple_spinner_dropdown_item, days);

        // 스피너에 값이 세팅된 어답터를 넣어준다.
        sp1.setAdapter(adapter1);

        // 스피너에 리스너를 등록한다
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp1_tv.setText(days[i]+"요일");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sp1_tv.setText(" ");
            }
        });
    }
}
