package com.example.sung.dementiacare.photo.TextDiary;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skm28 on 2017-10-04.
 */

public class TextDiaryDo implements Parcelable{

    private int ino;
    private String title;
    private String contents;
    private String date;

    public TextDiaryDo(int ino, String title, String contents, String date) {
        this.ino = ino;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public TextDiaryDo(String title, String contents, String date) {
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

    public static final Creator<TextDiaryDo> CREATOR
            = new Creator<TextDiaryDo>() {

        @Override
        public TextDiaryDo createFromParcel(Parcel in) {
            return new TextDiaryDo(in);
        }

        @Override
        public TextDiaryDo[] newArray(int size) {
            return new TextDiaryDo[size];
        }
    };

    private TextDiaryDo(Parcel in) {
        ino = in.readInt();
        title = in.readString();
        contents = in.readString();
        date = in.readString();
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
    }

}