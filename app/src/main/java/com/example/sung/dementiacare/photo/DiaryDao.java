package com.example.sung.dementiacare.photo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.sung.dementiacare.database.DBHelper;

import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class DiaryDao extends DBHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DiaryDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
    }

    public void insert(DiaryDo diaryDo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO DIARY VALUES(null, '" + diaryDo.getTitle() + "', '" + diaryDo.getContents() + "', '" + diaryDo.getDate() + "', '" + diaryDo.getImageUri().toString() + "');");
        db.close();
    }

    public void update(DiaryDo diaryDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE DIARY SET title ='" + diaryDo.getTitle() +"', contents = '" + diaryDo.getContents() +"' WHERE _id = '" + diaryDo.getIno() + "'");
        db.close();
    }

    public void delete(DiaryDo diaryDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM DIARY WHERE _id = '" + diaryDo.getIno() + "'");
        db.close();
    }

    public ArrayList<DiaryDo> getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DiaryDo> arrayList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM DIARY", null);
        while (cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String title = cursor.getString(1);
            String contents = cursor.getString(2);
            String date = cursor.getString(3);
            String image = cursor.getString(4);

            DiaryDo diaryDo = new DiaryDo(ino, title, contents, date, image);
            arrayList.add(diaryDo);
        }
        return arrayList;
    }

    public DiaryDo getResultByIno(int ino) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DIARY WHERE _id = " + Integer.toString(ino), null);
        cursor.moveToFirst();

        String title = cursor.getString(1);
        String contents = cursor.getString(2);
        String date = cursor.getString(3);
        String image = cursor.getString(4);

        DiaryDo diaryDo = new DiaryDo(ino, title, contents, date, image);

        return diaryDo;
    }

    public ArrayList<String> getTitles() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> titles = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT title FROM DIARY", null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            titles.add(title);
        }
        return titles;
    }

}

