package com.hongseokandrewjang.android.sqlitebasic_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "sqlite.db";
    public static final int DB_VERSION = 3;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 최초 설치시만 호출
        // assets에 잇는 파일을 복사해서 디스크로 옮긴다

        // scheme 버전 3으로 변경 -2016 - 10 - 12
        String scheme = "delete from bbs10";
        db.execSQL(scheme);
        for(int i=0;i<100;i++){
            String query = "insert into bbs10(title) values('everything "+i+"')";
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db버전이 높아지면 디스크에 있는 db에 덮어쓰기 한다.
        onCreate(db);
    }

    private static DBHelper dbHelper = null;

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLiteDatabase openDatabase(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
        return dbHelper.getWritableDatabase();
    }
}
