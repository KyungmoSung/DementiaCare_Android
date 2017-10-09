package com.example.sung.dementiacare.photo;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class PhotoDiaryDetailActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.iv_image)
    ImageView iv_image;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_contents)
    TextView tv_contents;

    @BindView(R.id.tv_date)
    TextView tv_date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_diary_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Uri imageUri = Uri.parse(intent.getStringExtra("image"));
        String title = intent.getStringExtra("title");
        String contents = intent.getStringExtra("contents");
        String date = intent.getStringExtra("date");

        if (intent.hasExtra("title")) {
            title = intent.getStringExtra("title");
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
            toolbar_title.setTextColor(Color.WHITE);
            toolbar_title.setText(title);
        }

        Glide.with(this)
                .load(imageUri)
                .into(iv_image);
        if (title.length() > 0) {
            tv_title.setText(title);
        } else {
            tv_title.setText("제목없음");
        }
        if (contents.length() > 0) {
            tv_contents.setText(contents);
        } else {
            tv_contents.setText("내용없음");
        }
        tv_date.setText(date);
    }

}