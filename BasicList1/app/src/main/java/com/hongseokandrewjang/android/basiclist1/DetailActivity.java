package com.hongseokandrewjang.android.basiclist1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView artistView;
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView)findViewById(R.id.img);
        artistView = (TextView)findViewById(R.id.artistText);
        titleView = (TextView)findViewById(R.id.titleText);

        Intent intent = getIntent();
        int image = 0;
        String title = "";
        String artist = "";
        RecyclerData data = null;

        int position = intent.getExtras().getInt("postiion"); // list data의 키값
        if (RecyclerAnimationActivity.datas != null) {
            data = RecyclerAnimationActivity.datas.get(position);


//        Bundle bundle = intent.getExtras();
//        data = (RecyclerData)bundle.getSerializable("datas");

//        title = intent.getExtras().getString("title");
//        artist = intent.getExtras().getString("artist");
//        image = intent.getExtras().getInt("image");
            title = data.title;
            artist = data.artist;
            image = data.image;
        }

        if (RecyclerCardActivity.datas != null) {
            data = RecyclerCardActivity.datas.get(position);
            title = data.title;
            artist = data.artist;
            image = data.image;
        }

        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(image);
        artistView.setText(artist);
        titleView.setText(title);
    }
}
