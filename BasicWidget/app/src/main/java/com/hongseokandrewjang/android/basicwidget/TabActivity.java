package com.hongseokandrewjang.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabActivity extends AppCompatActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        // Tab 1
        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab One");
        // 탭을 눌렀을 때 호출되는 View
        spec1.setContent(R.id.tab1);
        // 탭의 이름
        spec1.setIndicator("Tab 001");
        // 탭호스트에 탭을 담아준다
        tabHost.addTab(spec1);

        // Tab 1
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab two");
        // 탭을 눌렀을 때 호출되는 View
        spec2.setContent(R.id.tab2);
        // 탭의 이름
        spec2.setIndicator("Tab 002");
        // 탭호스트에 탭을 담아준다
        tabHost.addTab(spec2);

        // Tab 1
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab three");
        // 탭을 눌렀을 때 호출되는 View
        spec3.setContent(R.id.tab3);
        // 탭의 이름
        spec3.setIndicator("Tab 003");
        // 탭호스트에 탭을 담아준다
        tabHost.addTab(spec3);

    }
}
