package com.hongseokandrewjang.android.basiclist1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicList2Activity extends AppCompatActivity {

    ArrayList<Map<String, String>> datas;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list2);

        listView = (ListView)findViewById(R.id.listView2);
        setDatas();
    }

    private void setDatas(){
        datas = new ArrayList<Map<String, String>>();
        for(int i=0;i<26;i++) {
            Map<String, String> map = new HashMap<>();
            map.put("number",i+"");
            map.put("char",(char)('A'+i)+"");
            datas.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,                               // 1. 컨텍스트
                datas,                              // 2. 데이터
                R.layout.activity_basic_list2_item, // 3. 레이아웃
                new String[]{"number","char"},      // 4. datas에 들어가있는 맵의 key 값
                new int[]{R.id.text1, R.id.text2}// 5. 커스텀레이아웃의 view 아이디들
        );

        listView.setAdapter(adapter);

    }
}
