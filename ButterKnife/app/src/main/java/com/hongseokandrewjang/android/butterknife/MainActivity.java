package com.hongseokandrewjang.android.butterknife;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindBitmap;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView mTextView;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindString(R.string.butter_knife)
    String value;
    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnOnclick)
    public void onClickBind(){
        mTextView.setText("클릭됐음");
    }

    @OnClick(R.id.btnSetString)
    public void setString(){
        mTextView.setText(value);
    }

    @OnClick(R.id.btnSetImage)
    public void setImage(){
        mImageView.setImageBitmap(image);
    }
}
