package com.hongseokandrewjang.android.activitybasic02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    EditText et = null; // 이렇게 attribute 들을 초기화 하는 것은 좋지만
                        // 만약 data를 넣는다면 함수 내에서 넣어야 한다

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn = (Button) findViewById(R.id.btnFinish);
        et = (EditText)findViewById(R.id.editText2);


        Intent intent = getIntent(); // getIntent는 이 activity로 온 여러가지 intent중 가장 최근거
        // 를 얻는다

        // Bundle은 꾸러미다 -> 여러개의 값들이 올 수 있다
        Bundle bundle = intent.getExtras();

        String str = bundle.getString("inputText");
        et.setText(str);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 1. 에디트 텍스트에서 값을 가져온다
                String result = et.getText().toString();
                // 2. 인텐트를 생성하고 돌려줄 값을 셋팅한다
                // Intent는 component들 간의 연락이니까 공유하지 않는 것이 좋다
                Intent intent =  new Intent();
                intent.putExtra("return1", result);
                intent.putExtra("return2", "결과값2");
                // 3. setResult에 결과코드와 데이터를 넘겨준다
                setResult(1,intent);
                // 현재 액티비티 종료
                finish();
            }
        }
        );



    }


    // 문자열을 수식으로 변경한 후 결과값을 리턴하는 함수
    // 계산기에서 사용하면 됨
    // * 사용법 주요사항
    //  핸드폰 키보드에 입력받은

//    public String eval(String str){
//        String result = "";
//        ScriptEngineManager mgr = new ScriptEngineManager();
//        ScriptEngine engine = mgr.getEngineByName("JavaScript");
//        try{
//            result = engine.eval(str).toString();
//        }catch (ScriptException e){
//            e.printStackTrace();
//        }
//        return result;
//    }
}
