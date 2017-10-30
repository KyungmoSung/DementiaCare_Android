package com.example.sung.dementiacare.notification;

import android.widget.CheckBox;

import com.example.sung.dementiacare.notification.alarm.AlarmDo;

/**
 * Created by Minwoo on 2017. 9. 24..
 */

public class ListViewItem {
    private String text ;
    private CheckBox checkBox;
    private AlarmDo alarmDo;

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public AlarmDo getAlarmDo() {
        return alarmDo;
    }

    public void setAlarmDo(AlarmDo alarmDo) {
        this.alarmDo = alarmDo;
    }

    public void setText(String text) {
        this.text = text ;
    }
    public String getText() {
        return this.text ;
    }


    public boolean isChecked() {

        return this.checkBox.isChecked();
    }

    public void setChecked(boolean bool) {
        this.checkBox.setChecked(bool);
    }
}

