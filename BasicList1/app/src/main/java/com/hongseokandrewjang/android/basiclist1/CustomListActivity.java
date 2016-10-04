package com.hongseokandrewjang.android.basiclist1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    // Custom의 핵심은 adapter를 custom하는 것

    ListView listView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        listView = (ListView)findViewById(R.id.listView3);

        ArrayList<String> datas = new ArrayList<>();
        for(int i=0;i<30;i++){
            datas.add("data:"+i);
        }

        adapter = new CustomAdapter(this, datas);
        listView.setAdapter(adapter);
    }
}

class CustomAdapter extends BaseAdapter{

    Context context;            // 컨텍스트
    ArrayList datas;             // 데이터 배열
    LayoutInflater inflater;    // xml 파일을 instance 화 해서 메모리에 올려준다

    public CustomAdapter(Context context, ArrayList<String> datas){
        this.context = context;
        this.datas = datas;
        // 시스템에서 xml을 개체화 시켜주는 인플레이터를 가져온다
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() { // 자식뷰들의 개수를 리턴해준다
        return datas.size();
    }

    @Override
    public Object getItem(int i) { // 자식 뷰를 리턴해준다
        // 자식 뷰의 순서
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) { //자식 뷰의 id 값을 넘겨준다
        return i;
    }

    @Override
                        // 자식
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        // if문을 달아준거는 안달아주면 scroll할때마다 계속 만들기때문
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_item, null);
        }
        TextView tv = (TextView)convertView.findViewById(R.id.textH);
        tv.setText(datas.get(position).toString());
        return convertView;
    }
}