package com.hongseokandrewjang.android.layoutbasic01;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LayoutCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_layout_code);

        // 1. 레이아웃을 생성한다
        RelativeLayout layout = new RelativeLayout(this);


        // 2. 내부에 들어가는 위젯들을 생성한다
        Button button = new Button(this);
        Button button2 = new Button(this);
        // 2.1 위젯의 속성도 정의할 수 있다.
        button.setText("Button A");
        button.setBackgroundColor(Color.CYAN);

        button2.setText("Button B");
        button2.setBackgroundColor(Color.BLUE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LayoutCode.this, "버튼클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. 레이아웃을 설정한다
        RelativeLayout.LayoutParams buttonParameter = new RelativeLayout.LayoutParams(
                // 자동으로 가로, 세로로 입력되고 밑의 것을 통해서 설정가능
                // 이건 크기 설정하는 법
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        // 이건 버튼의 위치 정하는 법
        buttonParameter.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParameter.addRule(RelativeLayout.CENTER_VERTICAL);


        RelativeLayout.LayoutParams button2Parameter = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        button2Parameter.addRule(RelativeLayout.LEFT_OF);
        button2Parameter.addRule(RelativeLayout.ABOVE);
        // 3. 레이아웃에 위에서 생성한 위젯들을 더해준다
        layout.addView(button, buttonParameter);
        layout.addView(button2, button2Parameter);

        // 9. 최종적으로 액티비티 최상위 레이아웃 개체를 셋팅한다
        setContentView(layout);
    }
}
