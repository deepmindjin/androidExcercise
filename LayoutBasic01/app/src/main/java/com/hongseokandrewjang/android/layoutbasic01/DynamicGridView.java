package com.hongseokandrewjang.android.layoutbasic01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DynamicGridView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid_view);

        Button btn = (Button)findViewById(R.id.openDynamicGridBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DynamicGridView.this, DynamicGrid.class);
                startActivity(intent);
            }
        });

    }


}
