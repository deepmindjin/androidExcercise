package com.hongseokandrewjang.android.medialibrary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 장홍석 on 2016-10-04.
 */

public class cardDetail extends AppCompatActivity{

    ImageView imageView;
    TextView titleView;
    TextView artistView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_deatil);

        imageView = (ImageView)findViewById(R.id.showDetailImage);
        titleView = (TextView)findViewById(R.id.showDetailTitle);
        artistView = (TextView)findViewById(R.id.showDetailArtist);

        Intent intent = getIntent();
        Bitmap image = null;
        String title = "";
        String artist = "";
        cardData data = null;

        int position = intent.getExtras().getInt("POSITION"); // list data의 키값

        if (MainActivity.datas != null) {
            data = MainActivity.datas.get(position);
            title = data.title;
            image = data.image;
            artist = data.artist;
        }

        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageBitmap(image);
        titleView.setText(title);
        artistView.setText(artist);
    }
}
