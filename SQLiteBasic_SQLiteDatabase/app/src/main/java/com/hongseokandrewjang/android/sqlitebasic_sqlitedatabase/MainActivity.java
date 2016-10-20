package com.hongseokandrewjang.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView result;
    Button open;
    Button select;
    Button delete;
    Button insert;
    Button update;
    SQLiteDatabase db;

    private final static String TAG = "MAINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db!=null) {
                    // 쿼리를 실행해준다. select 문을 제외한 모든 쿼리에 사용
                   db.execSQL("insert into bbs4(name,title,contents) values('홍길동','글제목','내용')");
                    // 쿼리를 실행 후, 결과값을 Cursor 리턴해준다 즉... select문에 사용
//                    db.rawQuery(,null);
                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = null;
                if(db != null){
                    cursor = db.rawQuery("select * from bbs4",null);
                    while(cursor.moveToNext()){
                        int index = cursor.getColumnIndex("no"); // 컬럼며에 해당하는 순서를
                        String id = cursor.getString(index); // 순서로 컬럼을 가져온다.
                        index = cursor.getColumnIndex("name");
                        String name = cursor.getString(index);
                        index= cursor.getColumnIndex("title");
                        String title = cursor.getString(index);
                        index= cursor.getColumnIndex("ndate");
                        String date = cursor.getString(index);
                        String temp = result.getText().toString();
                        result.setText(temp + "id="+id+",   name="+name+",   title="+title+",    date="+date);
                    }
                }
            }
        });
    }

    private void init(){
        File file = new File(getFullpath("sqlite.db"));
        if(!file.exists())
            assetToDisk("sqlite.db");

        result = (TextView)findViewById(R.id.resultView);
        open = (Button)findViewById(R.id.btnOpenDatabase);
        select = (Button)findViewById(R.id.btnSelect);
        delete = (Button)findViewById(R.id.btnDelete);
        insert = (Button)findViewById(R.id.btnInsert);
        update = (Button)findViewById(R.id.btnUpdate);
        db = SQLiteDatabase.openDatabase(getFullpath("sqlite.db"),null,0);
    }

    public String getFullpath(String fileName){
        return getFilesDir().getAbsolutePath()+File.separator+fileName;
    }

    public void assetToDisk(String fileName){

        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            // 외부에서 작성된 sqlite db파일 사용하기
            // (Asset은 폰의 하드에 들어가긴하는데 바로 접근이 불가능한 asset 영역에 따로 존재한다)
            // 1. assets 에 담아둔 파일을 internal 혹은 external 공간으로 복사하기위해 읽어온다
            AssetManager manager = getAssets();
            // asset 에 파일이 없으면 exception 이 발생하여 아래 로직이 실행되지 않는다.
            is = manager.open(fileName);
            bis = new BufferedInputStream(is);
            // 2. 저장할 위치에 파일이 없으면 생성한다.
            String targetFile = getFullpath(fileName);
            Log.i(TAG, "targetFile ========" + targetFile);

            File file = new File(targetFile);
            if(!file.exists()){
                file.createNewFile();
            }

            // 3. outputStream을 생성해서 파일 내용을 쓴다
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            // 읽어올 데이터를 담아줄 변수
            int read = -1; // 모두 읽어오면 -1이 리턴된다
            // 한번에 읽을 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];
            // 더 이상 읽어올 데이터가 없을 때 까지 buffer 단위로 읽어서 쓴다
            while((read = bis.read(buffer, 0, 1024)) != -1){
                bos.write(buffer, 0, read);
            }
            // 남아있는 데이터를 buffer에서 써준다
            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
                if (bis != null) bis.close();
                if (is != null) is.close();
            }catch (Exception e){

            }
        }
    }
}
