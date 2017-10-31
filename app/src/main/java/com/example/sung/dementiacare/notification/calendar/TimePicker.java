package com.example.sung.dementiacare.notification.calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;

import java.util.Calendar;


/**
 * Created by kyungmo on 2017. 10. 31..
 */

public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    public Context context;
    Calendar calendar;
    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
//
//        calendar = Calendar.getInstance();
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int min = calendar.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(context,this,hour,min, DateFormat.is24HourFormat(context));

//        return timePickerDialog;
    }
}
