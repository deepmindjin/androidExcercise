package com.hongseokandrewjang.android.calculator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class CalculatorMainActivity extends AppCompatActivity {
    int inputNum = 0;
    int result = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_main);

        while(true){

        }
    }

    public void goTop(Button btn){
        ObjectAnimator ani = ObjectAnimator.ofFloat(btn,"rotation",90);
        ani.start();
    }

    public int showResult(){
        return result;
    }
}
