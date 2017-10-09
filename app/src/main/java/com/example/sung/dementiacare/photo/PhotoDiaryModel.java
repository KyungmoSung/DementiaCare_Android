package com.example.sung.dementiacare.photo;

import android.net.Uri;

/**
 * Created by skm28 on 2017-10-04.
 */

public class PhotoDiaryModel {

    Uri imageUri;
    String title;
    String contents;
    String date;

    public PhotoDiaryModel(Uri imageUri, String title, String contents, String date) {
        this.imageUri = imageUri;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }
}
