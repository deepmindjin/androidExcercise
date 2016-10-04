package com.hongseokandrewjang.android.medialibrary;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.FileDescriptor;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<cardData> datas = new ArrayList<>();
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            initData();
        else
            checkPermission();


    }

    @TargetApi(M)
    private void checkPermission(){
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            String permissionArray[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissionArray,REQUEST_CODE);
        }else{
            initData();
        }
    }

    public void initData(){
        datas = getMusicInfo();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        cardAdapter adapter = new cardAdapter(datas, R.layout.cardviewlayout,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<cardData> getMusicInfo(){
        ArrayList<cardData> datas = new ArrayList<>();

        String projections[] = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST
        };
        // getContentResolver().query(주소, 검색해올 컬럼명들, 조건절, 조건절에 매핑되는 값, 정렬)
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projections, null, null, null);
        /*
        - url           : content://스키마(url주소) 형태로 정해져 있는 곳의 데이터를 가져온다
        - projection    : 가져올 컬럼 이름들의 배열. null을 입력하면 모든 데이터를 가져오는데 performance가 느려진다
        - selection     : 조건절(where)에 해당하는 내용
        - selection arguments : 조건절이 prepared statement 형태일 때, ? 에 매핑되는 값의 배열
        - sort order    : 정렬 조건
         */

        if(cursor != null){
            while(cursor.moveToNext()){
                cardData data = new cardData();
                // 데이터에 가수 이름을 입력
                // 1. 가수 이름 컬럼의 순서(index)를 가져온다
                int index = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                // 2. 해당 index를 가진 컬럼의 실제값을 가져온다
                data.artist = cursor.getString(index);

                index = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                data.title = cursor.getString(index);
                index = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);

                long albumId = cursor.getLong(index);
                Bitmap bitmap = getAlbumart(albumId);
                data.image = bitmap;
                index= cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                data.musicId = cursor.getString(index);

                datas.add(data);
            }
        }
        cursor.close();     // 이거안하면 앱이 엄청느려짐
        return datas;
    }

    public Bitmap getAlbumart(Long album_id) {
        Bitmap bm = null;
        Bitmap resized = null;
        try {
            final Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");

            Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);

            ParcelFileDescriptor pfd = this.getContentResolver()
                    .openFileDescriptor(uri, "r");

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;


            if (pfd != null) {
                FileDescriptor fd = pfd.getFileDescriptor();
                bm = BitmapFactory.decodeFileDescriptor(fd);
                resized = Bitmap.createScaledBitmap(bm, 500, 500, true);

            }
        } catch (Exception e) {
        }
        return resized;
    }


}
