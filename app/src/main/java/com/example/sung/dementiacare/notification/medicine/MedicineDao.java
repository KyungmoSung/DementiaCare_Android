package com.example.sung.dementiacare.notification.medicine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.sung.dementiacare.database.DBHelper;

import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class MedicineDao extends DBHelper {



    // If you change the database schema, you must increment the database version.

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public MedicineDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
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

