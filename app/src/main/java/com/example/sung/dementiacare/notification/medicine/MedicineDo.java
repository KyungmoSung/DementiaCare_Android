package com.example.sung.dementiacare.notification.medicine;

/**
 * Created by Minwoo on 2017. 10. 5..
 */

public class MedicineDo {


    private int ino;
    private String name;
    private String image_path_name;
    private int alarm;

    public MedicineDo(int ino, String name, String image_path_name, int alarm) {
        this.ino = ino;
        this.name = name;
        this.image_path_name = image_path_name;
        this.alarm = alarm;
    }

    public MedicineDo(String name, String image_path_name, int alarm) {
        this.name = name;
        this.image_path_name = image_path_name;
        this.alarm = alarm;
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

    public String getImage_path_name() {
        return image_path_name;
    }

    public void setImage_path_name(String image_path_name) {
        this.image_path_name = image_path_name;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return name;
    }
}
