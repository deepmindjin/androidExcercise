package com.hongseokandrewjang.android.fragmentbasic_tablayout_viewpage;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/*
    1. Gradle에 Design library를 추가해야한다.
    2. Main.xml에 TabLayout이랑 ViewPager를 추가해야한다.
 */

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    static final int FRAGMENT_COUNT = 4;
    ViewPager pager;
    HomeFragment home;
    MapFragment map;
    ETCFragment etc;
    BlankFragment settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragment를 생성
        home = new HomeFragment();
        map = new MapFragment();
        etc = new ETCFragment();
        // settings = new SettingsFragment();
        settings = BlankFragment.newInstance("","");

        // Tab Layout을 달아준다
        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("HOME"));
        tab.addTab(tab.newTab().setText("MAP"));
        tab.addTab(tab.newTab().setText("ETC"));
        tab.addTab(tab.newTab().setText("SETTINGS"));

        // Fragment 를 달아준다
        pager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        // 페이저가 변경되었을 때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        // 탭에 페이저를 연결 해 주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

    }

    @Override
    public void onFragmentInteraction(String str) {
        if(str.equals("Blank")) {
            Toast.makeText(this, "서브 프래그먼트에서 클릭됨", Toast.LENGTH_SHORT).show();
        }
    }

    // 생성자, getCount, getItem 3개는 꼭 해야됨
    class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0: fragment = home; break;
                case 1: fragment = map; break;
                case 2: fragment = etc; break;
                case 3: fragment = settings; break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // 이렇게 하면 내가 쓸 만큼의 view page를 만들 수 있다.
            return FRAGMENT_COUNT;
        }
    }





}
