package com.hongseokandrewjang.android.sqlite_memopad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hongseokandrewjang.android.sqlite_memopad.com.hongseokandrewjang.sqlite_memopad.domain.Memo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        Memo memo = new Memo();
        memo.setContents("ㅎㅎㅎㅎ");
        memo.setNdate(dbHelper.getTimeStamp());
        memo.setImage("이미지");
        Log.i("시간",dbHelper.getTimeStamp());
        dbHelper.dbInsert(memo);

//        Memo data = dbHelper.dbSelectOne(1);
//        Log.i("MEMO",data.getNo()+"");
//        Log.i("MEMO",data.getContents());
//        Log.i("MEMO",data.getNdate());
//        Log.i("MEMO",data.getImage());

        ArrayList<Memo> datas = dbHelper.dbSelectAll();
        for(Memo data : datas){
            Log.i("MEMO", "no = "+data.getNo());
            Log.i("MEMO", "date = "+data.getNdate());
            Log.i("MEMO", "contents = "+data.getContents());
            Log.i("MEMO", "image = "+data.getImage());
        }
    }
}
