package com.hongseokandrewjang.android.broadcastreceiver01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {


    // 브로드 캐스트 되는 것들이 많을 텐데 id가 있어야함. 그래서 id를 정의해준다
    static final String BROADCAST_ACTION = "com.hongseokandrewjang.android.MESSAGE";
                                            // ↑ 이부분은 보통 겹치지 말라고 full package 써준다
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(BROADCAST_ACTION)){

            // Activity 화면을 띄워준다
            Intent i = new Intent(context, MapsActivity.class);

            Bundle bundle = intent.getExtras();
            String str = bundle.getCharSequence("msg").toString();
            Log.i("asdf",str);

            // Activity가 실행될 때 상태를 설정해 주는것이 Flag
            i.addFlags(
                    // NEW TASK는 새로운 Activity를 만든다
                    i.FLAG_ACTIVITY_NEW_TASK
                    // CLEAR TOP은 맨 위에꺼 빼고 다 지운다
                    | i.FLAG_ACTIVITY_CLEAR_TOP);
            // Broad Cast Receiver는 context가 없어서 Context를 써줘야한다.

            context.startActivity(i);
        }
    }
}
