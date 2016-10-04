package com.hongseokandrewjang.android.clock;

import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String PENDING_RESULT = "pending_result";
    public static final String RESULT = "result";
    public static final int RESULT_CODE = "ok".hashCode();
    private static final int REQUEST_CODE = 0;

    public static final String ACTION = "com.kodonho.android.ACTION_SPINBAR";
    Button bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (Button) findViewById(R.id.bar);

    }

        // 이 부분은 Broad cast receiver를 이용한건데 좋지는 않음음
//   @Override
//    protected void onResume() {
//        super.onResume();
//
//        Intent intent = getIntent();
//        if(intent != null){
//            Bundle bundle = intent.getExtras();
//            if(bundle!=null && bundle.get("angle") != null) {
//                int angle = bundle.getInt("angle");
//                spinBar(bar, angle);
//            }
//        }
//    }

    public void startService(View v){
        PendingIntent pending = createPendingResult(REQUEST_CODE, new Intent(), 0);
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(PENDING_RESULT, pending);
        startService(intent);
    }

    protected void onActivityResult(int req, int res, Intent data) {
        if (req == REQUEST_CODE && res == RESULT_CODE) {
            int result = data.getExtras().getInt("key1");
            spinBar(bar, result);
        }
        super.onActivityResult(req, res, data);
    }

    public void spinBar(View v, int angle){
        ObjectAnimator ani = ObjectAnimator.ofFloat(v,"rotation",angle);
        ani.setDuration(1000);
        ani.start();
    }

}
