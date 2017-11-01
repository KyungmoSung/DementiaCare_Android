package com.example.sung.dementiacare.notification.calendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sung.dementiacare.database.DBHelper;

import java.util.ArrayList;

/**
 * Created by Sung on 2017. 10. 5..
 */

public class CalendarDao extends DBHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public CalendarDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
    }

    public void insert(CalendarDo calendarDo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO CALENDAR VALUES(null, '" + calendarDo.getDate() + "', '" + calendarDo.getsTime() + "', '" + calendarDo.geteTime() + "', '" + calendarDo.getTitle() + "', '" + calendarDo.getContents() + "');");
        db.close();
    }

    public void update(CalendarDo calendarDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE CALENDAR SET date ='" + calendarDo.getDate() +"',sTime ='" + calendarDo.getsTime() +"',eTime ='" + calendarDo.geteTime() +"',title ='" + calendarDo.getTitle() +"', contents = '" + calendarDo.getContents() +"' WHERE _id = '" + calendarDo.getIno() + "'");
        db.close();
    }

    public void delete(CalendarDo calendarDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM CALENDAR WHERE _id = '" + calendarDo.getIno() + "'");
        db.close();
    }

    public ArrayList<CalendarDo> getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CalendarDo> arrayList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CALENDAR", null);
        while (cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String date = cursor.getString(1);
            String sTime = cursor.getString(2);
            String eTime = cursor.getString(3);
            String title = cursor.getString(4);
            String contents = cursor.getString(5);

            CalendarDo calendarDo = new CalendarDo(ino, date, sTime, eTime, title,contents);
            arrayList.add(calendarDo);
        }
        return arrayList;
    }

    public CalendarDo getResultByIno(int ino) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CALENDAR WHERE _id = " + Integer.toString(ino), null);
        cursor.moveToFirst();

        String date = cursor.getString(1);
        String sTime = cursor.getString(2);
        String eTime = cursor.getString(3);
        String title = cursor.getString(4);
        String contents = cursor.getString(5);

        CalendarDo calendarDo = new CalendarDo(ino, date, sTime, eTime, title, contents);

        return calendarDo;
    }

    public ArrayList<String> getTitles() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> titles = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT title FROM CALENDAR", null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            titles.add(title);
        }
        return titles;
    }
}

