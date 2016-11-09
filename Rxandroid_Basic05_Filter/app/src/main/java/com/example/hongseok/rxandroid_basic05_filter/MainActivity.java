package com.example.hongseok.rxandroid_basic05_filter;

import android.database.Observable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnFilter,btnFor,btnFirst,btnLast,btnDistinct,btnTake,btnGroup;
    Integer dataset[] = {1,2,3,1,4,5,3,6,7,8,7,5,9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFor = (Button)findViewById(R.id.btnForEach);
        btnFor.setOnClickListener(this);
        btnFilter = (Button)findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this);
        btnFirst = (Button)findViewById(R.id.btnFirst);
        btnFirst.setOnClickListener(this);
        btnLast = (Button)findViewById(R.id.btnLast);
        btnLast.setOnClickListener(this);
        btnDistinct = (Button)findViewById(R.id.btnDistinct);
        btnDistinct.setOnClickListener(this);
        btnTake = (Button)findViewById(R.id.btnTake);
        btnTake.setOnClickListener(this);
        btnGroup = (Button)findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnForEach:
                foreach();
                break;
            case R.id.btnFilter:
                filter();
                break;
            case R.id.btnFirst:
                first();
                break;
            case R.id.btnLast:
                last();
                break;
            case R.id.btnDistinct:
                distinct();
                break;
            case R.id.btnTake:
                take(3);
                break;
            case R.id.btnGroup:
                group();
                break;
        }
    }

    public void filter(){
        rx.Observable.from(dataset)
            .filter(item -> item%2 == 0)
            .subscribe(result -> System.out.print(result));
    }
    public void take(int count){
        rx.Observable.from(dataset)
                .take(count)
                .subscribe(result -> System.out.print(result));
    }
    public void foreach(){
        rx.Observable.from(dataset)
                .forEach(result -> System.out.print(result));
    }
    public void distinct(){
        rx.Observable.from(dataset)
                .distinct()
                .subscribe(result -> System.out.print(result));
    }
    public void first(){
        rx.Observable.from(dataset)
                .first()
                .subscribe(result -> System.out.print(result));
    }
    public void last(){
        rx.Observable.from(dataset)
                .last()
                .subscribe(result -> System.out.print(result));
    }
    public void group(){
        rx.Observable.from(dataset)
                .groupBy(item -> item%2 == 0)
                .subscribe(grouped -> grouped.toList().subscribe(
                        items -> Log.w("GROUP",items+" Even : "+grouped.getKey())
                ));
    }

    public void print(int item){
        System.out.print(item);
    }
}
