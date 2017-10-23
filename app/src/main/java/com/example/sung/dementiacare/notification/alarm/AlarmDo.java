package com.example.sung.dementiacare.notification.alarm;


/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class AlarmDo {

    private int ino;
    private String name;
    private int hour;
    private int minutes;
    private int repeat;

    public AlarmDo(String name, int hour, int minutes, int repeat) {
        this.name = name;
        this.hour = hour;
        this.minutes = minutes;
        this.repeat = repeat;
    }

    public AlarmDo(int ino, String name, int hour, int minutes, int repeat) {
        this.ino = ino;
        this.name = name;
        this.hour = hour;
        this.minutes = minutes;
        this.repeat = repeat;
    }

    public int getIno() {
        return ino;
    }

    public void setIno(int ino) {
        this.ino = ino;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}
