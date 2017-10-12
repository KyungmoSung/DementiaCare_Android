package com.example.sung.dementiacare.photo.TextDiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sung.dementiacare.database.DBHelper;

import java.util.ArrayList;

/**
 * Created by Sung on 2017. 10. 5..
 */

public class TextDiaryDao extends DBHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public TextDiaryDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
    }

    public void insert(TextDiaryDo textDiaryDo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO TEXTDIARY VALUES(null, '" + textDiaryDo.getTitle() + "', '" + textDiaryDo.getContents() + "', '" + textDiaryDo.getDate() + "');");
        db.close();
    }

    public void update(TextDiaryDo textDiaryDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE TEXTDIARY SET title ='" + textDiaryDo.getTitle() +"', contents = '" + textDiaryDo.getContents() +"' WHERE _id = '" + textDiaryDo.getIno() + "'");
        db.close();
    }

    public void delete(TextDiaryDo textDiaryDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM TEXTDIARY WHERE _id = '" + textDiaryDo.getIno() + "'");
        db.close();
    }

    public ArrayList<TextDiaryDo> getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TextDiaryDo> arrayList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TEXTDIARY", null);
        while (cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String title = cursor.getString(1);
            String contents = cursor.getString(2);
            String date = cursor.getString(3);

            TextDiaryDo textDiaryDo = new TextDiaryDo(ino, title, contents, date);
            arrayList.add(textDiaryDo);
        }
        return arrayList;
    }

    public TextDiaryDo getResultByIno(int ino) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TEXTDIARY WHERE _id = " + Integer.toString(ino), null);
        cursor.moveToFirst();

        String title = cursor.getString(1);
        String contents = cursor.getString(2);
        String date = cursor.getString(3);

        TextDiaryDo textDiaryDo = new TextDiaryDo(ino, title, contents, date);

        return textDiaryDo;
    }

    public ArrayList<String> getTitles() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> titles = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT title FROM TEXTDIARY", null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            titles.add(title);
        }
        return titles;
    }

}

