package com.example.sung.dementiacare.notification.alarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class AlarmDao extends SQLiteOpenHelper {


    private final static int DATABASE_VERSION = 1;

    // If you change the database schema, you must increment the database version.


    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public AlarmDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, "DemetiaCare.db", factory, DATABASE_VERSION);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE ALARM (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, alarm INTEGER);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
