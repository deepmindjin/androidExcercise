package com.kodonho.android.fragmentbasic_tablayout_viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/*
    1. 그래들에 design 라이브러리 추가
    2. main xml 에 TabLayut , ViewPager 추가
 */
public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    static final int FRAGMENT_COUNT = 4;
    HomeFragment home;
    MapFragment map;
    EtcFragment etc;
    Fragment settings;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new HomeFragment();
        map = new MapFragment();
        etc = new EtcFragment();
        settings = BlankFragment.newInstance("","");

        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("Etc"));
        tab.addTab(tab.newTab().setText("Settings"));

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        // 페이저가 변경되었을때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        // 탭에 페이저를 연결해주는 리스너
        tab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager) );

        // 페이저와 탭의 개수가 다를때는 자체구현
//        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(    ) {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                pager.setCurrentItem(tab.getPosition());
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        Toast.makeText(this, "서브 프래그먼트에서 클릭됨", Toast.LENGTH_SHORT).show();
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0: fragment = home; break;
                case 1: fragment = map; break;
                case 2: fragment = etc; break;
                case 3: fragment = settings; break;
            }
            return fragment;
        }
    }
}
