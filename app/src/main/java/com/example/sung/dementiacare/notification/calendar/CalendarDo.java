package com.example.sung.dementiacare.notification.calendar;


/**
 * Created by Sung on 2017. 10. 5..
 */

public class CalendarDo {

    private int ino;
    private String date;
    private String sTime;
    private String eTime;
    private String title;
    private String contents;

    public CalendarDo(int ino, String date, String sTime, String eTime, String title, String contents) {
        this.ino = ino;
        this.date = date;
        this.sTime = sTime;
        this.eTime = eTime;
        this.title = title;
        this.contents = contents;
    }

    public CalendarDo(String date, String sTime, String eTime, String title, String contents) {
        this.date = date;
        this.sTime = sTime;
        this.eTime = eTime;
        this.title = title;
        this.contents = contents;
    }

    public CalendarDo(String date) {
        this.date = date;
    }

    public int getIno() {
        return ino;
    }

    public void setIno(int ino) {
        this.ino = ino;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

}
