package com.example.sung.dementiacare.notification.alarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sung.dementiacare.database.DBHelper;

import java.text.SimpleDateFormat;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class AlarmDao extends DBHelper {


    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public AlarmDao(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, factory);

    }

}
