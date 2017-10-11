package com.example.sung.dementiacare.notification.hopemessage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sung.dementiacare.database.DBHelper;
import com.example.sung.dementiacare.notification.information.InformationDo;

/**
 * Created by Minwoo on 2017. 10. 7..
 */

public class MessageDao extends DBHelper {


    private final static int DATABASE_VERSION = 1;

    // If you change the database schema, you must increment the database version.


    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public MessageDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);

    }


    public MessageDo resultByRandom() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MESSAGE ORDER BY RANDOM() LIMIT 1;", null);
        cursor.moveToFirst();

        int ino = cursor.getInt(0);
        String title = cursor.getString(1);
        String text = cursor.getString(2);

        return new MessageDo(ino, title, text);


    }

}