package com.hongseokandrewjang.android.materialdesign_propertyanimation_spreadcube;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    boolean spread = false;
    Button red, black, yellow, blue, accent, primary, primaryDark, green, brown;
    int btnWidth = 0;
    int btnHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toggle(View v){
        Button btn = (Button)findViewById(R.id.toggleBtn);

        red = (Button)findViewById(R.id.redBtn);
        black = (Button)findViewById(R.id.blackBtn);
        yellow = (Button)findViewById(R.id.yellowBtn);
        blue = (Button)findViewById(R.id.blueBtn);
        accent = (Button)findViewById(R.id.accentBtn);
        primary = (Button)findViewById(R.id.primaryBtn);
        primaryDark = (Button)findViewById(R.id.primaryDarkBtn);
        green = (Button)findViewById(R.id.greenBtn);
        brown = (Button)findViewById(R.id.brownBtn);
        Button[] colors = {red, black, blue, yellow, accent, primary, primaryDark, green, brown};


        if(spread == true){
            spread = false;
            spreadCube(colors);
            btn.setText("COMBINE");
        }else{
            spread = true;
            combineCube(colors);
            btn.setText("SPREAD");
        }

    }

    public void spreadCube(Button[] btns){
        int count = 0;
        float x = 0;
        float y = 0;
        int btnHeight = 0;
        int btnWidth = 0;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                btnHeight = btns[count].getHeight();
                btnWidth = btns[count].getWidth();
                x =  (i-1)*btnWidth;
                y =  (j-1)*btnHeight;

                ObjectAnimator ani1 = ObjectAnimator.ofFloat(btns[count], "translationX", x);
                ObjectAnimator ani2 = ObjectAnimator.ofFloat(btns[count], "translationY", y);
                AnimatorSet aniSet = new AnimatorSet();
                aniSet.playTogether(ani1,ani2);
                aniSet.start();
                count = count + 1;
            }
        }
    }

    public void combineCube(Button[] btns){
        int count = 0;
        float x = 0;
        float y = 0;
        int btnHeight = 0;
        int btnWidth = 0;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                ObjectAnimator ani1 = ObjectAnimator.ofFloat(btns[count], "translationX", x);
                ObjectAnimator ani2 = ObjectAnimator.ofFloat(btns[count], "translationY", y);
                AnimatorSet aniSet = new AnimatorSet();
                aniSet.playTogether(ani1,ani2);
                aniSet.start();
                count = count + 1;
            }
        }
    }

}
