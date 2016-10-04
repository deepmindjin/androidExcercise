package com.hongseokandrewjang.android.activitybasic02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText2);

        // Find the button and set the OnClickListener so that the activity can recognize
        // the click
        btn = (Button) findViewById(R.id.btnSend);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       // 이렇게 new를 해버리면 전에 있던 intent가 알아서 없어지고
                                       // garbage collector가 잘 해준다
                                       Intent intent = new Intent(MainActivity.this, SubActivity.class);

                                       // intent에 data 값을 넣어서 보낸다
                                       intent.putExtra("inputText", et.getText().toString());

                                       // 요것만 실행하면 바로 sub으로 일단 가고, 데이터도 보냄
                                       // 근데 startActivity를 쓰면 값이 가기만하고 보내지는 못한다
                                       // 그래서 startActivityForResult를 쓰면 값을 받을 수 있다
                                       // requestCode는 내가 나중에 돌려 받을 때 어디에서 온 건지
                                       // 알 수 있게 내가 임의로 int로 code를 지정해준다.
                                       startActivityForResult(intent,0);
                                   }
                               }
        );



//    // Intent is used to sending various information and settings
//    // when we set the Intent, we put every line for actions that we want to be taken
//    // so that when we send the Intent, it acts as i put before
//
//    public void toSub(View v){
//        Intent intent = new Intent(MainActivity.this, SubActivity.class);
//        startActivity(intent);
//    }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        // Send 버튼이 호출했었던 것
        if(requestCode == 0){
            if(resultCode == 1){
                Bundle bundle = data.getExtras();
                String result = bundle.getString("returnText");
                et.setText(result);
            }
        }
    }

}
