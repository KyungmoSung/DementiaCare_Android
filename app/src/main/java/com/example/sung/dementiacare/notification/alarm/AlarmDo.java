package com.example.sung.dementiacare.notification.alarm;

import android.widget.TimePicker;

import java.text.SimpleDateFormat;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class AlarmDo {

    private int ino;
    private String name;
    private TimePicker timePicker;
    private int repeat;

    public AlarmDo(String name, TimePicker timePicker, int repeat) {
        this.name = name;
        this.timePicker = timePicker;
        this.repeat = repeat;
    }

    public AlarmDo(int ino, String name, TimePicker timePicker, int repeat) {
        this.ino = ino;
        this.name = name;
        this.timePicker = timePicker;
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public TimePicker getTimePicker() {
        return timePicker;
    }

    public int getRepeat() {
        return repeat;
    }
}
