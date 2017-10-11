package com.example.sung.dementiacare.notification.hopemessage;

/**
 * Created by Minwoo on 2017. 10. 7..
 */

public class MessageDo {

    int ino;
    String title;
    String text;

    public MessageDo(int ino, String title, String text) {
        this.ino = ino;
        this.title = title;
        this.text = text;
    }

    public int getIno() {
        return ino;
    }

    public void setIno(int ino) {
        this.ino = ino;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
