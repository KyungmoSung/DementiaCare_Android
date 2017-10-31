package com.example.sung.dementiacare.notification;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kyungmo on 2017. 10. 30..
 */

public class TodayDialog extends Dialog {
    @BindView(R.id.tv_title)
    protected TextView tv_title;
    @BindView(R.id.tv_contents)
    protected TextView tv_contents;
    private String addCategoryStr;

    public TodayDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 바 삭제
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_today);
        ButterKnife.bind(this);
    }

    public void setMessage(String title, String contents){
        tv_title.setText(title);
        tv_contents.setText(contents);
    }
}
