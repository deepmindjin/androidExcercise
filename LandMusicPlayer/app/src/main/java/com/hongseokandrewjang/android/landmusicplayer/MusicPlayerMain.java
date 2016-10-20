package com.hongseokandrewjang.android.landmusicplayer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.hongseokandrewjang.android.landmusicplayer.MusicPlayerMain.FRAGMENT_COUNT;

public class MusicPlayerMain extends AppCompatActivity {

    static final int FRAGMENT_COUNT = 2;
    ViewPager pager;
    ListFragment listFragment;
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_main);

        listFragment = new ListFragment();
        detailFragment = new DetailFragment();

        pager = (ViewPager)findViewById(R.id.viewPager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);


    }

    private void setData(){
        for(int i=0;i<100;i++){
            MusicData music = MusicData();
            music.artist = ""+i+"번 째 아티스트 입니다";
            music.title = "Song Number : "+i;

        }
    }
}

class PagerAdapter extends android.support.v4.view.PagerAdapter {
    Data data;
    Context context;
    LayoutInflater inflater;

    public PagerAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = detailFragment;
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // return super.instantiateItem(container, position);
        View view = inflater.inflate(R.layout.fragment_detail,null);
        return view;
    }

    @Override
    public int getCount() {
        // 이렇게 하면 내가 쓸 만큼의 view page를 만들 수 있다.
        return FRAGMENT_COUNT;
    }
}

