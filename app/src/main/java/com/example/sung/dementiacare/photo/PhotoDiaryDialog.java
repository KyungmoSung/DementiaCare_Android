package com.example.sung.dementiacare.photo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by skm28 on 2017-10-09.
 */

public class PhotoDiaryDialog extends Dialog {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.title_edit_text)
    TextInputEditText et_title;

    @BindView(R.id.contents_edit_text)
    TextInputEditText et_contents;

    PhotoDiaryModel photoDiary;
    Uri imageUri;

    public PhotoDiaryDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_photo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_cancel)
    public void clickCancel() {
        photoDiary = null;
        dismiss();
    }

    @OnClick(R.id.button_save)
    public void clickSave() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("yy.MM.dd");
        String datetime = dateformat.format(c.getTime());
        photoDiary = new PhotoDiaryModel(imageUri, et_title.getText().toString(), et_contents.getText().toString(), datetime);
        dismiss();
    }

    public PhotoDiaryModel getPhotoDiary() {
        return photoDiary;
    }

    public void setImage(Uri imageUri) {
        this.imageUri = imageUri;
        Glide.with(getContext())
                .load(imageUri)
                .into(imageView);
    }
}
