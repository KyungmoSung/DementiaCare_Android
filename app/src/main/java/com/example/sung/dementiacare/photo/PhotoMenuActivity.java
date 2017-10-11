package com.example.sung.dementiacare.photo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.sung.dementiacare.MainActivity;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.support.SupportActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gun0912.tedbottompicker.TedBottomPicker;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class PhotoMenuActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_photo);
        ButterKnife.bind(this);

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("사진");
    }

    @OnClick(R.id.photo_diary_layout)
    public void showPhotoDiary() {
        startActivity(new Intent(this, PhotoDiaryActivity.class));
    }

    @OnClick(R.id.text_diary_layout)
    public void showTextDiary() {
        startActivity(new Intent(this, TextDiaryActivity.class));
    }
}