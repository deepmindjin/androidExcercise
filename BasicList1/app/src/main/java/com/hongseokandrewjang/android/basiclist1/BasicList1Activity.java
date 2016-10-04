package com.hongseokandrewjang.android.basiclist1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BasicList1Activity extends AppCompatActivity {

    String[] datas = {"백향목", "김동진", "김태원", "임재민", "김도형", "석주영", "장홍석", "김해든"};
    // 데이터를 담을 객체
    // Adapter는 110v와 220v 코드와 같아서 담는 대상이 달라져도 같이 사용 할 수 있게 해준다
    ArrayAdapter<String> adapter;
    // 데이터를 담은 adapter를 받는 리스트뷰
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list1);

        // parameter의 순서는        context,     layout,                           data
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked,datas);

        /*--2. 아이템 레이아웃 종류
        simple_list_item_2                  텍스트뷰 두개로 구성
        simple_list_item_Checked            끝에 체크 박스가 포함됨
        simple_list_item_single_choice      끝에 라디오 버튼 생성
        simple_list_item_multiple_choice    끝에 체크 박스가 생성
         */

        listView = (ListView)findViewById(R.id.listView);
        // 선택모드 활성화
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
    }
}
