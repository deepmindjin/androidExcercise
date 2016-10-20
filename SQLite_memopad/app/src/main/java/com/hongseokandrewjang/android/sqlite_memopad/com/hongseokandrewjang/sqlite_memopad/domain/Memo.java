package com.hongseokandrewjang.android.sqlite_memopad.com.hongseokandrewjang.sqlite_memopad.domain;

/**
 * Created by HongSeokAndrewJang on 2016-10-13.
 */

public class Memo {
    int no;
    String contents;
    String ndate;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }
}
