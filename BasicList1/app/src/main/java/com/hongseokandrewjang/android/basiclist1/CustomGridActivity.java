package com.hongseokandrewjang.android.basiclist1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class CustomGridActivity extends AppCompatActivity {

    GridView gridView;
    CustomGridAdapter customGridAdapter;
    ArrayList<Griditem> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_grid);

        // ------------------------------------------
        // Data를 직접 여기서만 입력해줌 (예시라서)
        for(int i=0;i<100;i++){
            Griditem item = new Griditem();
            item.title = "Bang!";
            item.num = i;

            datas.add(item);
        }
        // -------------------------------------------

        gridView = (GridView)findViewById(R.id.gridView2);
        customGridAdapter = new CustomGridAdapter(this, datas, R.layout.activity_custom_grid_item);
        gridView.setAdapter(customGridAdapter);
    }
}
class Griditem{
    String title;
    int num;
}
