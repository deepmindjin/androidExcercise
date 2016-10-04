package com.hongseokandrewjang.android.layoutbasic01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class DynamicGrid extends AppCompatActivity {

    GridLayout gridView;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid);

        Button button = (Button)findViewById(R.id.makeBtn);
        gridView = (GridLayout) findViewById(R.id.gridView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count + 1;
                Button newButton = new Button(DynamicGrid.this);

                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridView.removeView(view);
                    }
                });

                newButton.setText(String.valueOf(count));
                gridView.addView(newButton);
            }
        });
    }
}
