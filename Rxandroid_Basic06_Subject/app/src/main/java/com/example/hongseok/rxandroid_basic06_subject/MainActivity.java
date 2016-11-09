package com.example.hongseok.rxandroid_basic06_subject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPublish, btnBehavior, btnReplay, btnAsync, btnAsyncComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPublish = (Button)findViewById(R.id.btnPublish);
        btnPublish.setOnClickListener(this);
        btnBehavior = (Button)findViewById(R.id.btnBehavior);
        btnBehavior.setOnClickListener(this);
        btnReplay = (Button)findViewById(R.id.btnReplay);
        btnReplay.setOnClickListener(this);
        btnAsync = (Button)findViewById(R.id.btnAsync);
        btnAsync.setOnClickListener(this);
        btnAsyncComplete = (Button)findViewById(R.id.btnAsyncComplete);
        btnAsyncComplete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPublish:
                publish();
                break;
            case R.id.btnBehavior:
                behave();
                break;
            case R.id.btnReplay:
                replay();
                break;
            case R.id.btnAsync:
                async();
                break;
            case R.id.btnAsyncComplete:
                asyncComplete();
                break;
        }
    }

    // 구독한 시점부터 발행된 아이템을 받는다
    public void publish(){
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item -> Log.e("Publish","item="+item));
        subject.onNext("D");
        subject.onNext("E");
    }
    // 가장 최근에 관찰된 아이템부터 구독한다
    public void behave(){
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item -> Log.e("Behavior","item="+item));
        subject.onNext("D");
        subject.onNext("E");
    }
    public void replay(){
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item -> Log.e("Replay","item="+item));
        subject.onNext("D");
        subject.onNext("E");
    }
    public void async(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item -> Log.e("Async","item="+item));
        subject.onNext("D");
        subject.onNext("E");
    }
    public void asyncComplete(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item -> Log.e("AsyncCompleted","item="+item));
        subject.onNext("D");
        subject.onNext("E");
        subject.onCompleted();
    }
}
