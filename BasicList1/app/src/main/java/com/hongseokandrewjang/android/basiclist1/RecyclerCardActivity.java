package com.hongseokandrewjang.android.basiclist1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {

    public static ArrayList<RecyclerData> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        for (int i = 0; i < 100; i++) {
            RecyclerData data = new RecyclerData();
            data.title = i + " Homer's Song";
            data.image = R.mipmap.nobrain;
            data.artist = i + " The simpson";
            datas.add(data);
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerCardView);
        RecyclerCardAdapter adapter = new RecyclerCardAdapter(datas, R.layout.activity_recycler_card_item,this);
        recyclerView.setAdapter(adapter);

//        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
//        RecyclerView.LayoutManager manager1
//        RecyclerView.LayoutManager manager2

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
