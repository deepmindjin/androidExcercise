package com.hongseokandrewjang.android.fragmentbasic03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Listitem> datas = new ArrayList<>();

    Fragment listFragment;
    Fragment detailFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0;i<100;i++){
            Listitem data = new Listitem();
            data.title = "제목 : "+ i;
            data.artist = "가수 : " + i + "입니다";
            datas.add(data);
        }

        listFragment = new FragmentOne();
        detailFragment = new FragmentTwo();


        setFragment();


    }

    public void setOne(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentList, listFragment);
        transaction.commit();
    }

    public void setTwo(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentDetail, detailFragment);
        transaction.commit();
    }

    public void setFragment(){
        setOne();
        setTwo();
    }
}
