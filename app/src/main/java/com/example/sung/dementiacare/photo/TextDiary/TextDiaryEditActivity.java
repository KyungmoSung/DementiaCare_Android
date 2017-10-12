package com.example.sung.dementiacare.photo.TextDiary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 10. 12..
 */

public class TextDiaryEditActivity extends AppCompatActivity {

    TextDiaryDo textDiaryDo;
    TextDiaryDao textDiaryDao;
    String date = null;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.et_title)
    EditText et_title;

    @BindView(R.id.et_contents)
    EditText et_contents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_diary_edit);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPhoto));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("일기 쓰기");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        textDiaryDao = new TextDiaryDao(getApplicationContext(), null);

        Intent intent = getIntent();
        if(intent.hasExtra("diary")) {
            textDiaryDo = intent.getParcelableExtra("diary");
            et_title.setText(textDiaryDo.getTitle());
            et_contents.setText(textDiaryDo.getContents());
            date = textDiaryDo.getDate();
        }
    }

    @OnClick(R.id.btn_save)
    public void save(){
        String newDate;
        if(date == null){
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("yy.MM.dd");
            newDate = dateformat.format(c.getTime());
        } else {
            newDate = date;
        }
        textDiaryDo = new TextDiaryDo(et_title.getText().toString(), et_contents.getText().toString(), newDate);
        textDiaryDao.insert(textDiaryDo);
        finish();
    }
}
