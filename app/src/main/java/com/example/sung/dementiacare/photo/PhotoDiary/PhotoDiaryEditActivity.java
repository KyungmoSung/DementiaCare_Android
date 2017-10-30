package com.example.sung.dementiacare.photo.PhotoDiary;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.photo.TextDiary.TextDiaryDao;
import com.example.sung.dementiacare.photo.TextDiary.TextDiaryDo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 10. 12..
 */

public class PhotoDiaryEditActivity extends AppCompatActivity {

    String imageUri;
    PhotoDiaryDo photoDiaryDo;
    PhotoDiaryDao photoDiaryDao;
    String date = null;
    boolean editMode = false;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.iv_image)
    ImageView iv_image;

    @BindView(R.id.et_title)
    EditText et_title;

    @BindView(R.id.et_contents)
    EditText et_contents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_diary_edit);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPhoto));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPhoto));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("사진 추가");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        photoDiaryDao = new PhotoDiaryDao(getApplicationContext(), null);

        Intent intent = getIntent();
        if(intent.hasExtra("imageUri")) {
            imageUri = intent.getStringExtra("imageUri");
            setImage(imageUri);
        }

        if(intent.hasExtra("diary")) {
            photoDiaryDo = intent.getParcelableExtra("diary");
            et_title.setText(photoDiaryDo.getTitle());
            et_contents.setText(photoDiaryDo.getContents());
            date = photoDiaryDo.getDate();
            setImage(photoDiaryDo.getImageUri().toString());
            editMode = true;
        }
    }

    @OnClick(R.id.btn_save)
    public void save(){
        if(editMode == false) {
            String newDate;
            if (date == null) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("yy.MM.dd");
                newDate = dateformat.format(c.getTime());
            } else {
                newDate = date;
            }
            photoDiaryDo = new PhotoDiaryDo(et_title.getText().toString(), et_contents.getText().toString(), newDate, imageUri);
            photoDiaryDao.insert(photoDiaryDo);
            finish();
        } else {
            photoDiaryDo.setTitle(et_title.getText().toString());
            photoDiaryDo.setContents(et_contents.getText().toString());
            photoDiaryDao.update(photoDiaryDo);
            finish();
        }
    }

    public void setImage(String imageUri) {
        this.imageUri = imageUri;
        Glide.with(this)
                .load(Uri.parse(imageUri))
                .into(iv_image);
    }
}
