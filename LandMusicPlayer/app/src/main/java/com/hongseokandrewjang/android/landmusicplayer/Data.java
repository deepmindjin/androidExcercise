package com.hongseokandrewjang.android.landmusicplayer;

import java.util.ArrayList;


public class Data {
    ArrayList<MusicObserver> observers = new ArrayList<>();
    ArrayList<MusicData> data = new ArrayList<>();
    int position = -1;

    public void attach(MusicObserver observer ){
        observers.add(observer);
    }

    public interface MusicObserver{
        public void update();
    }

    public int getCount(){
        return data.size();
    }
}

class MusicData{
    String artist;
    String title;
    int position;

}

