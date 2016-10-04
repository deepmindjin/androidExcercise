package com.hongseokandrewjang.android.basiclist1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);

        // 아답터에 넘겨줄 데이터 정의
        ArrayList<ExpandData> datas = new ArrayList<>();
        ExpandData data = new ExpandData();
        data.cityName = "서울";
        data.guNames.add("강동");
        data.guNames.add("강서");
        data.guNames.add("강남");
        data.guNames.add("강북");
        data.guNames.add("마포");
        data.guNames.add("서초");
        data.guNames.add("동작");
        data.guNames.add("이태원");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "광주";
        data.guNames.add("광산");
        data.guNames.add("중구");
        data.guNames.add("북구");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "부산";
        data.guNames.add("동래");
        data.guNames.add("수영");
        data.guNames.add("해운대");
        data.guNames.add("영도");
        data.guNames.add("중구");
        datas.add(data);

        ExpandableAdapter ea = new ExpandableAdapter(
                this,
                R.layout.expand_parent_item,
                R.layout.expand_child_item,
                datas);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        // 버전별 인터케이터 위치 수정
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2)
            expandableListView.setIndicatorBounds(width-getPixelFromDisplay(50),width-getPixelFromDisplay(10));
        else
            expandableListView.setIndicatorBoundsRelative(width-getPixelFromDisplay(50),width-getPixelFromDisplay(10));

        // Expandable Adapter를 만든다
        ExpandableAdapter adapter = new ExpandableAdapter(this, R.layout.expand_parent_item, R.layout.expand_child_item, datas);
        expandableListView.setAdapter(ea);

        // dp를 픽셀로 변활할 때
        int converedPixel = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()
                                          // ↑dp 값 입력
        );
    }

    public int getPixelFromDisplay(float pixels){
        // 화면 밀도 스케일
        final float scale = getResources().getDisplayMetrics().density;
        // 컨버팅 dps -> pixel - 화면 밀도 스케일을 기준으로
        return (int)(pixels * scale + 0.5f);
    }

    public int pxToDp(Context context, int px){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (metrics.xdpi / metrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(Context context, int dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (metrics.xdpi / metrics.DENSITY_DEFAULT));
        return  px;
    }
}
