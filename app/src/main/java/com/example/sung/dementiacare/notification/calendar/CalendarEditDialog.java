package com.example.sung.dementiacare.notification.calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyungmo on 2017. 10. 30..
 */

public class CalendarEditDialog extends Dialog {

    @BindView(R.id.tv_date)
    protected TextView tv_date;
    @BindView(R.id.btn_stime)
    protected Button btn_stime;
    @BindView(R.id.btn_etime)
    protected Button btn_etime;
    @BindView(R.id.et_title)
    protected EditText et_title;
    @BindView(R.id.et_contents)
    protected EditText et_contents;

    CalendarDo calendarDo;
    CalendarDao calendarDao;
    public Boolean isEditMode = false;

    public CalendarEditDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 바 삭제
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_edit_event);
        ButterKnife.bind(this);
        calendarDao = new CalendarDao(getContext(), null);
    }

    public void setMessage(String date, String stime, String etime, String title, String contents){
        tv_date.setText(date);
        btn_stime.setText(stime);
        btn_etime.setText(etime);
        et_title.setText(title);
        et_contents.setText(contents);
    }

    public void setMessage(String date){
        tv_date.setText(date);
    }

    @OnClick(R.id.btn_ok)
    public void saveData(){
        if(isEditMode) {
            calendarDo.setDate(tv_date.getText().toString());
            calendarDo.setsTime(btn_stime.getText().toString());
            calendarDo.seteTime(btn_etime.getText().toString());
            calendarDo.setTitle(et_title.getText().toString());
            calendarDo.setContents(et_contents.getText().toString());
            calendarDao.update(calendarDo);
            dismiss();
        }else {
            calendarDo = new CalendarDo(tv_date.getText().toString(), btn_stime.getText().toString(), btn_etime.getText().toString(), et_title.getText().toString(), et_contents.getText().toString());
            calendarDao.insert(calendarDo);
            dismiss();
        }
    }

    @OnClick(R.id.btn_cancel)
    public void cancelDialog(){
        dismiss();
    }
}
