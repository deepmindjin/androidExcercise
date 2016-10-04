package com.hongseokandrewjang.android.basiclist1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAnimationActivity extends AppCompatActivity {

    // 1. 리사이클러뷰를 현재 액티비티의 메인 레이아웃에 만든다.
    // 2, 리사이클러뷰에 들어갈 아이템 레이아웃을 만든다.
    // *-- 들어갈 데이터는 정의해줬음 --*
    // 3. Adapter를 만든다
    // 4. 리사이클러뷰에 아답터를 셋팅한다
    // 5. 리사이클러뷰에 레이아웃매니저를 지정한다다

    public static ArrayList<RecyclerData> datas = new ArrayList<>();

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_animation);

       // *--------------- 들어갈 데이터 ---------------------

        for(int i=0;i<100;i++){
            RecyclerData data = new RecyclerData();
            data.title = i + " Homer's Song";
            data.image = R.mipmap.nobrain;
            data.artist = i + " The simpson";
            datas.add(data);
        }
       // *--------------- 들어갈 데이터 ---------------------

       RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerAnimationView);
       RecyclerAnimationAdapter adapter = new RecyclerAnimationAdapter(datas, R.layout.recycler_animation_item,this);
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
