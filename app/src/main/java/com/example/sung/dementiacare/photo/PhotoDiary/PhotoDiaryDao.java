package com.example.sung.dementiacare.photo.PhotoDiary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sung.dementiacare.database.DBHelper;

import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class PhotoDiaryDao extends DBHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public PhotoDiaryDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);
    }

    public void insert(PhotoDiaryDo photoDiaryDo) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO PHOTODIARY VALUES(null, '" + photoDiaryDo.getTitle() + "', '" + photoDiaryDo.getContents() + "', '" + photoDiaryDo.getDate() + "', '" + photoDiaryDo.getImageUri().toString() + "');");
        db.close();
    }

    public void update(PhotoDiaryDo photoDiaryDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE PHOTODIARY SET title ='" + photoDiaryDo.getTitle() +"', contents = '" + photoDiaryDo.getContents() +"' WHERE _id = '" + photoDiaryDo.getIno() + "'");
        db.close();
    }

    public void delete(PhotoDiaryDo photoDiaryDo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM PHOTODIARY WHERE _id = '" + photoDiaryDo.getIno() + "'");
        db.close();
    }

    public ArrayList<PhotoDiaryDo> getResults() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<PhotoDiaryDo> arrayList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM PHOTODIARY", null);
        while (cursor.moveToNext()) {
            int ino = cursor.getInt(0);
            String title = cursor.getString(1);
            String contents = cursor.getString(2);
            String date = cursor.getString(3);
            String image = cursor.getString(4);

            PhotoDiaryDo photoDiaryDo = new PhotoDiaryDo(ino, title, contents, date, image);
            arrayList.add(photoDiaryDo);
        }
        return arrayList;
    }

    public PhotoDiaryDo getResultByIno(int ino) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PHOTODIARY WHERE _id = " + Integer.toString(ino), null);
        cursor.moveToFirst();

        String title = cursor.getString(1);
        String contents = cursor.getString(2);
        String date = cursor.getString(3);
        String image = cursor.getString(4);

        PhotoDiaryDo photoDiaryDo = new PhotoDiaryDo(ino, title, contents, date, image);

        return photoDiaryDo;
    }

    public ArrayList<String> getTitles() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> titles = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT title FROM PHOTODIARY", null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            titles.add(title);
        }
        return titles;
    }

}

