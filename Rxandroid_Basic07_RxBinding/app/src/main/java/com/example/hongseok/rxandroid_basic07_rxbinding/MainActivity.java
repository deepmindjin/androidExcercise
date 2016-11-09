package com.example.hongseok.rxandroid_basic07_rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxView
                .clicks(findViewById(R.id.btnBind))
                .map(event -> new Random().nextInt())
                .subscribe(
                        rand -> ((TextView)findViewById(R.id.textView)).setText("Value="+rand)
                );

        Observable<String> leftObs = RxView.clicks(findViewById(R.id.btnLeft))
                .map(event -> "Left");

        Observable<String> rightObs = RxView.clicks(findViewById(R.id.btnRight))
                .map(event -> "Right");

        Observable.merge(leftObs,rightObs)
                .subscribe(
                        text -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                );

        // Text Change Event
        RxTextView
                .textChangeEvents((EditText)findViewById(R.id.etSearch))
                .subscribe(
                        word -> Log.w("SEARCH","word:"+word.text().toString())
                );
    }
}
