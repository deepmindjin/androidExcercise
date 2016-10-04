package com.hongseokandrewjang.android.criminalintent;

import java.util.UUID;

/**
 * Created by 장홍석 on 2016-10-04.
 */
public class Crime {

    private UUID id;
    private String title;

    public crime(){
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
