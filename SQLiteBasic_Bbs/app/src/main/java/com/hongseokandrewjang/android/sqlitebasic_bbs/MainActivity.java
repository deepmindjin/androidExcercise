package com.hongseokandrewjang.android.sqlitebasic_bbs;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    public final static int ACTION_WRITE = 0;
    public final static int ACTION_SAVE = 1;
    public final static int ACTION_CANCEL = 2;
    public final static int ACTION_MODIFY = 3;
    public final static int ACTION_DETAIL = 4;

    public ArrayList<BbsData> datas = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    SQLiteDatabase db;
    EditFragment editFragment;
    ListFragment listFragment;
    DetailFragment detailFragment;
    ViewPager pager = null;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        init();

        editFragment = new EditFragment();
        listFragment = new ListFragment();
        detailFragment = new DetailFragment();
        fragments.add(listFragment);
        fragments.add(editFragment);
        fragments.add(detailFragment);

        pager = (ViewPager)findViewById(R.id.viewPagerOnMain);
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(int actionFlag) {
        switch (actionFlag){
            case ACTION_WRITE:
                pager.setCurrentItem(1);
                break;
            case ACTION_SAVE:
                pager.setCurrentItem(0);
                break;
            case ACTION_CANCEL:
                pager.setCurrentItem(0);
                break;
            case ACTION_MODIFY:
                pager.setCurrentItem(1);
                break;
            case ACTION_DETAIL:
                pager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public ArrayList<BbsData> getDatas() {
        ArrayList<BbsData> result = datas;
        return result;
    }

    @Override
    public BbsData getData(int position) {
        BbsData result = datas.get(position);
        return result;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void addData(String title, String name, String contents) {
        if(db!=null) {
            db.execSQL("insert into bbs(name,title,contents) values('"+name+","+title+","+contents+")");
        }
        BbsData data = new BbsData();
        data.contents = contents;
        data.name = name;
        data.ndate = "2015-12-13";
        data.no = 0;
        data.title = title;
        datas.add(data);
    }

    class CustomAdapter extends FragmentStatePagerAdapter {

        public CustomAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            fragments = list;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragments.get(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    public void setData(){
        for(int i=0;i<15;i++){
            BbsData data = new BbsData();
            data.contents = "내용물입니다.내용물입니다.내용물입니다.내용물입니다.내용물입니다.";
            data.name = "홍길동";
            data.ndate = "2015-12-13";
            data.no = i+1;
            data.title = "제목";
            datas.add(data);
        }
    }





    private void init(){
        File file = new File(getFullpath("sqlite.db"));
        if(!file.exists())
            assetToDisk("sqlite.db");
        db = SQLiteDatabase.openDatabase(getFullpath("sqlite.db"),null,0);

    }

    public void searchDatas(){
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
//                String temp = result.getText().toString();
//                result.setText(temp + "id="+id+",   name="+name+",   title="+title+",    date="+date);
            }
        }
    }

    public String getFullpath(String fileName){
        return getFilesDir().getAbsolutePath()+ File.separator+fileName;
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


