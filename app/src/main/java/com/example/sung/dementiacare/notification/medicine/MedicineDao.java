package com.example.sung.dementiacare.notification.medicine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class MedicineDao extends SQLiteOpenHelper {


    private final static int DATABASE_VERSION = 1;

    // If you change the database schema, you must increment the database version.


    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public MedicineDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, "DemetiaCare.db", factory, DATABASE_VERSION);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE MEDICINE (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image_path_name TEXT, alarm INTEGER);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(MedicineDo medicineDo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MEDICINE VALUES(null, '" + medicineDo.getName() + "', '" + medicineDo.getImage_path_name() + "', '" + medicineDo.getAlarm() + "');");
        db.close();
    }

    public ArrayList<MedicineDo> getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<MedicineDo> arrayList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MEDICINE", null);
        while (cursor.moveToNext()) {

            int ino = cursor.getInt(0);
            String name = cursor.getString(1);
            String image_path_name = cursor.getString(2);
            int alarm = cursor.getInt(3);

            MedicineDo medicineDo = new MedicineDo(ino, name, image_path_name, alarm);
            arrayList.add(medicineDo);
        }

        return arrayList;
    }

    public MedicineDo getResultByIno(int ino) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MEDICINE WHERE _id = "+Integer.toString(ino), null);

        cursor.moveToFirst();

        String name = cursor.getString(1);
        String image_path_name = cursor.getString(2);
        int alarm = cursor.getInt(3);

        MedicineDo medicineDo = new MedicineDo(ino, name, image_path_name, alarm);


        return medicineDo;
    }


    public ArrayList<String> getNames() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> names = new ArrayList<>();


        Cursor cursor = db.rawQuery("SELECT name FROM MEDICINE", null);
        while (cursor.moveToNext()) {

            String name = cursor.getString(0);
            names.add(name);
        }

        return names;

    }
}

