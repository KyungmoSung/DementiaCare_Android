package com.example.sung.dementiacare.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.information.InformationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by skm28 on 2018-08-26.
 */

public class EventDialog extends Dialog {
    @BindView(R.id.tv_title)
    protected TextView tv_title;
    @BindView(R.id.tv_contents)
    protected TextView tv_contents;
    protected String youtubeUrl;
    public EventDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 바 삭제
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_event);
        ButterKnife.bind(this);
    }

    public void setMessage(String title, String contents, String url){
        tv_title.setText(title);
        tv_contents.setText(contents);
        youtubeUrl = url;
    }

    @OnClick(R.id.btn_video)
    void openVideo(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+youtubeUrl));
        getContext().startActivity(browserIntent);
        dismiss();
    }

    @OnClick(R.id.btn_close)
    void closeDialog(){
        dismiss();
    }

    @OnClick(R.id.btn_close_oneday)
    void closeDialogForOneDay(){
        savePreferences();
        dismiss();
    }

    // 값 저장하기
    private void savePreferences(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(c.getTime());

        SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("eventCheckUpDate", strDate);
        Log.e("eventCheckUpDate",strDate);
        editor.commit();
    }

}

