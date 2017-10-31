package com.example.sung.dementiacare.information.Environment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by skm28 on 2017-10-04.
 */

public class EnvironmentDo implements Parcelable{

    private String title;
    private String webUri;
    private String imageUri;

    public EnvironmentDo(String title, String webUri, String imageUri) {
        this.title = title;
        this.webUri = webUri;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUri() {
        return webUri;
    }

    public void setWebUri(String webUri) {
        this.webUri = webUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public static final Creator<EnvironmentDo> CREATOR
            = new Creator<EnvironmentDo>() {

        @Override
        public EnvironmentDo createFromParcel(Parcel in) {
            return new EnvironmentDo(in);
        }

        @Override
        public EnvironmentDo[] newArray(int size) {
            return new EnvironmentDo[size];
        }
    };

    private EnvironmentDo(Parcel in) {
        title = in.readString();
        webUri = in.readString();
        imageUri = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(webUri);
        parcel.writeString(imageUri);
    }

}