package com.example.sung.dementiacare.notification.alarm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TimePicker;

import com.example.sung.dementiacare.database.DBHelper;
import com.example.sung.dementiacare.notification.medicine.MedicineDo;

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

        TimePicker timePicker = alarmDo.getTimePicker();

        timePicker.getCurrentHour();
        timePicker.getCurrentMinute();

        db.execSQL("INSERT INTO DEMENTIACARE_ALARM VALUES(null, '" + alarmDo.getName() + "', '" + timePicker.getCurrentHour() + "-"+timePicker.getCurrentMinute()+"', '" + alarmDo.getRepeat() + "');");
        db.close();
    }
}

