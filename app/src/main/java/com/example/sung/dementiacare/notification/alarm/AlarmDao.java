package com.example.sung.dementiacare.notification.alarm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.RelativeDateTimeFormatter;
import android.widget.TimePicker;

import com.example.sung.dementiacare.database.DBHelper;
import com.example.sung.dementiacare.notification.medicine.MedicineDo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class AlarmDao extends DBHelper {



    // If you change the database schema, you must increment the database version.

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public AlarmDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
    }


    public void insert(AlarmDo alarmDo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가

        db.execSQL("INSERT INTO DEMENTIACARE_ALARM VALUES(null, '" + alarmDo.getName() + "', '" + alarmDo.getHour() + "', '"+alarmDo.getMinutes()+"', '" + alarmDo.getRepeat() + "');");
        db.close();
    }

    public ArrayList<AlarmDo> getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<AlarmDo> arrayList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM DEMENTIACARE_ALARM", null);
        while (cursor.moveToNext()) {

            int ino = cursor.getInt(0);
            String name = cursor.getString(1);
            int hour = cursor.getInt(2);
            int minutes = cursor.getInt(2);
            int repeat = cursor.getInt(4);

            AlarmDo alarmdo = new AlarmDo(ino, name, hour, minutes, repeat);
            arrayList.add(alarmdo);
        }

        return arrayList;
    }
    public ArrayList<AlarmDo> getResultsByMedicineId(long id) {

        SQLiteDatabase db = getReadableDatabase();

        ArrayList<AlarmDo> arrayList = new ArrayList<>();
        String[] args = { "A.DEMENTIACARE_ALARM__id", Long.toString(id) };
        Cursor cursor = db.rawQuery("SELECT B._id,B.title,B.hour,B.minutes,B.repeat FROM DEMENTIACARE_ALARM_ATTACH A, DEMENTIACARE_ALARM B WHERE A.DEMENTIACARE_ALARM__id = B._id AND A.MEDICINE__id = "+Long.toString(id),null);

        while (cursor.moveToNext()) {

            int ino = cursor.getInt(0);
            String name = cursor.getString(1);
            int hour = cursor.getInt(2);
            int minutes = cursor.getInt(2);
            int repeat = cursor.getInt(4);

            AlarmDo alarmdo = new AlarmDo(ino, name, hour, minutes, repeat);

            arrayList.add(alarmdo);
        }
        db.close();

        return arrayList;
    }
}

