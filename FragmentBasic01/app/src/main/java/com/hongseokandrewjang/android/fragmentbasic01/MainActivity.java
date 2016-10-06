package com.hongseokandrewjang.android.fragmentbasic01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonOne:
                        goOne();
                        break;
                    case R.id.radioButtonTwo:
                        goTwo();
                        break;
                }
            }
        });

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

    }

    public void goOne(){
        FragmentManager manager = getSupportFragmentManager();
        // Fragment는 transaction단위로 움직인다.
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentMain,fragmentOne);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void goTwo(){
        FragmentManager manager = getSupportFragmentManager();
        // Fragment는 transaction단위로 움직인다.
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentMain,fragmentTwo);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
