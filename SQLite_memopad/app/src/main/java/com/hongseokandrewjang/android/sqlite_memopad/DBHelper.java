package com.hongseokandrewjang.android.sqlite_memopad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hongseokandrewjang.android.sqlite_memopad.com.hongseokandrewjang.sqlite_memopad.domain.Memo;

import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper{

    ArrayList<Memo> backupDatas = new ArrayList<>();
    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION = 2;

    // C-insert R-select 1개 R-select전체 U-update D-delete 쿼리를 만들어준다
    public void dbInsert(Memo memo){
        // db를 연결하고
        SQLiteDatabase db = getWritableDatabase();
        // 쿼리를 생성하고
        String query = "insert into memo(contents,image,ndate) values('" +
                memo.getContents()+"','" +
                memo.getImage()+"','" +
                memo.getNdate()+"')";
        db.execSQL(query);
        // db를 사용후 닫는다
        db.close();
    }

    public Memo dbSelectOne(int no){ // Select는 Table의 Key를 기준으로 값을 받는다
        Memo memo = new Memo(); // 리턴타입에 맞게 객체를 생성해주고
        ArrayList<Memo> datas = dbSelectAll();
        memo = datas.get(no-1);
        return memo; // 위에서 정의된 리턴 변수를 넘겨준다
    }

    public ArrayList<Memo> dbSelectAll(){
        String query;
        ArrayList<Memo> datas = new ArrayList<>();
        // 1. 디비를 open 읽기모드로
        SQLiteDatabase db = getReadableDatabase();
        // 2. select 쿼리를 만들고
        query = "select * from memo";
        // 3. 쿼리를 실행해서 커서에 담고
        Cursor cursor = db.rawQuery(query,null);
        // 4. 커서에 담긴 데이터를 while 문을 돌면서 꺼내고
        while(cursor.moveToNext()){
            // 5.1 Memo 데이터 단위로 cursor에서 꺼내와서 담아준다
            Memo data = new Memo();
            int index = cursor.getColumnIndex("no");
            data.setNo(cursor.getInt(index));
            index = cursor.getColumnIndex("contents");
            data.setContents(cursor.getString(index));
            index = cursor.getColumnIndex("ndate");
            data.setNdate(cursor.getString(index));
            index = cursor.getColumnIndex("image");
            data.setImage(cursor.getString(index));
            // 5.2 data.add(메모데이터)
            datas.add(data);
        }
        cursor.close();
        db.close();
        return datas;
    }

    public void dbUpdate(Memo memo){

    }

    public void dbDelete(int no){

    }

    public String getTimeStamp(){
        long timemillis = System.currentTimeMillis();
        Date date = new Date(timemillis);
        String time = date.toString();
        return time;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        // db version 1 - 최초등록 2016-10-13 11:30
//        String scheme_01 = "create table memo (no integer primary key autoincrement not null" +
//                ", contents text not null" +
//                ", ndate text not null)";
//        // 실행해서 table을 생성한다
//        db.execSQL(scheme_01);

        // db version 2 - upgrade 2016-10-13 14:08
        String scheme_02 = "create table memo (no integer primary key autoincrement not null" +
                ", contents text not null" +
                ", ndate text not null" +
                ", image text)";
        db.execSQL(scheme_02);

        for(Memo data : backupDatas){
            String query = "insert into memo(no, contents, ndate) values('" +
                    data.getNo()+"','" +
                    data.getContents()+"','" +
                    data.getNdate()+"')";
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 이전 데이터 베이스 버전에 따라 처리방식이 달라진다
        if(oldVersion == 1){
            String query = "select * from memo order by no";
            Cursor cursor = db.rawQuery(query,null);
            while(cursor.moveToNext()){
                Memo data = new Memo();
                int index = cursor.getColumnIndex("no");
                data.setNo(cursor.getInt(index));
                index = cursor.getColumnIndex("contents");
                data.setContents(cursor.getString(index));
                index = cursor.getColumnIndex("ndate");
                data.setNdate(cursor.getString(index));
                backupDatas.add(data);
            }
        }else if(oldVersion == 2 ){
            // 컬럼이 4개
        }
        String scheme_01_drop = "drop table memo";
        db.execSQL(scheme_01_drop);

        onCreate(db);
    }
}
