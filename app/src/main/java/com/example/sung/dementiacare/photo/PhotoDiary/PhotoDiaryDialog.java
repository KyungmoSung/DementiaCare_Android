package com.example.sung.dementiacare.photo.PhotoDiary;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.photo.PhotoDiary.PhotoDiaryDao;
import com.example.sung.dementiacare.photo.PhotoDiary.PhotoDiaryDo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by skm28 on 2017-10-09.
 */

public class PhotoDiaryDialog extends Dialog {
    String imageUri;
    PhotoDiaryDo photoDiaryDo;
    PhotoDiaryDao photoDiaryDao;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.layout_title)
    TextInputLayout layout_title;
    @BindView(R.id.layout_contents)
    TextInputLayout layout_contents;

    @BindView(R.id.input_title)
    TextInputEditText input_title;
    @BindView(R.id.input_contents)
    TextInputEditText input_contents;



    public PhotoDiaryDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_photo);
        ButterKnife.bind(this);

        photoDiaryDao = new PhotoDiaryDao(getContext(), null);

        input_title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    layout_title.setHint("제목");
                } else {
                    if (input_title.getText().toString().length() == 0) {
                        layout_title.setHint("제목을 입력해주세요");
                    }
                }
            }
        });

        input_contents.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    layout_contents.setHint("내용");
                } else {
                    if (input_contents.getText().toString().length() == 0) {
                        layout_contents.setHint("내용을 입력해주세요");
                    }
                }
            }
        });

    }

    @OnClick(R.id.button_cancel)
    public void clickCancel() {
        photoDiaryDo = null;
        dismiss();
    }

    @OnClick(R.id.button_save)
    public void clickSave() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("yy.MM.dd");
        String datetime = dateformat.format(c.getTime());
        photoDiaryDo = new PhotoDiaryDo(input_title.getText().toString(), input_contents.getText().toString(), datetime, imageUri);

        photoDiaryDao.insert(photoDiaryDo);

        dismiss();
    }

    public PhotoDiaryDo getPhotoDiary() {
        return photoDiaryDo;
    }

    public void setImage(String imageUri) {
        this.imageUri = imageUri;
        Glide.with(getContext())
                .load(Uri.parse(imageUri))
                .into(imageView);
    }
}
