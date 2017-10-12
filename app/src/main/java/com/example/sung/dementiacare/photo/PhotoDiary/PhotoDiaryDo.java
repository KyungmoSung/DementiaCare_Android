package com.example.sung.dementiacare.photo.PhotoDiary;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skm28 on 2017-10-04.
 */

public class PhotoDiaryDo implements Parcelable{

    private int ino;
    private String title;
    private String contents;
    private String date;
    private String imageUri;

    public PhotoDiaryDo(int ino, String title, String contents, String date, String imageUri) {
        this.ino = ino;
        this.imageUri = imageUri;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public PhotoDiaryDo(String title, String contents, String date, String imageUri) {
        this.imageUri = imageUri;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public PhotoDiaryDo(String title, String contents, String date) {
        this.title = title;
        this.contents = contents;
        this.date = date;
    }


    public int getIno() {
        return ino;
    }

    public void setIno(int ino) {
        this.ino = ino;
    }

    public Uri getImageUri() {
        return Uri.parse(imageUri);
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static final Creator<PhotoDiaryDo> CREATOR
            = new Creator<PhotoDiaryDo>() {

        @Override
        public PhotoDiaryDo createFromParcel(Parcel in) {
            return new PhotoDiaryDo(in);
        }

        @Override
        public PhotoDiaryDo[] newArray(int size) {
            return new PhotoDiaryDo[size];
        }
    };

    private PhotoDiaryDo(Parcel in) {
        ino = in.readInt();
        title = in.readString();
        contents = in.readString();
        date = in.readString();
        imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ino);
        parcel.writeString(title);
        parcel.writeString(contents);
        parcel.writeString(date);
        parcel.writeString(imageUri);
    }

}