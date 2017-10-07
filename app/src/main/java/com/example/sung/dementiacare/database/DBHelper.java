package com.example.sung.dementiacare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Minwoo on 2017. 10. 5..
 */
public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private final static int DATABASE_VERSION = 1;


    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {


        super(context, "DemetiaCare.db", factory, DATABASE_VERSION);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE MEDICINE (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image_path_name TEXT, alarm INTEGER);");

        db.execSQL("CREATE TABLE ALARM (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, alarm INTEGER);");

        db.execSQL("CREATE TABLE INFORMATION (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, text TEXT);");
        db.execSQL("INSERT INTO INFORMATION VALUES(null, '치매어르신도 존중받아야 할 사람임을 잊지 말아야 합니다.', '인지기능의 손상이 있더라도, 치매어르신은 여전히 자신의 성격과 취향이 있고, 아름다운 추억의 단편들을 지니고 있는 한 사람임을 잊지 말아야 합니다. 따라서 배려한다는 이유로 마냥 아이처럼 대해서는 안되며, 여전히 가족으로부터 존중과 사랑을 받고 있으며, 가정에서 나름의 역할이 있다고 느낄 수 있도록 배려해야 합니다.');");
        db.execSQL("INSERT INTO INFORMATION VALUES(null, '치매어르신의 신체적 건강에 대한 세심한 관심으로 적절한 건강관리를 받도록 합니다', '치매어르신은 자신의 신체 증상을 느끼고 표현하는 능력이 많이 부족합니다 그러다 보면 진단이나 치료의 적기를 놓쳐 작은 병을 크게 키우기도 하고 약화된 신체 질환 때문에 치매가 악화되기도 합니다. 가족들은 치매어르신이 제대로 표현하지 못하는 불편감이 없는지를 파악해야 합니다');");
        db.execSQL("INSERT INTO INFORMATION VALUES(null, '', '');");

        db.execSQL("CREATE TABLE MESSAGE (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, text TEXT);");


    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}