package com.example.sung.dementiacare.photo.PhotoDiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.photo.PhotoDiary.PhotoDiaryDo;
import com.example.sung.dementiacare.photo.TextDiary.TextDiaryDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skm28 on 2017-10-04.
 */

public class PhotoDiaryAdapter extends BaseAdapter {
    Context context;
    ArrayList<PhotoDiaryDo> data;
    LayoutInflater inf;

    public PhotoDiaryAdapter(Context context, ArrayList<PhotoDiaryDo> data) {
        this.context = context;
        this.data = data;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PhotoDiaryDo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int size = getContext().getResources().getDisplayMetrics().widthPixels / 3;
        if (convertView == null) {
            convertView = inf.inflate(R.layout.grid_item_photo, null);
            convertView.setLayoutParams(new GridView.LayoutParams(size, size));
        }

        ImageView ImageView = (ImageView) convertView.findViewById(R.id.grid_item_image);

        Glide.with(getContext())
                .load(getItem(position).getImageUri())
                .into(ImageView);

        return convertView;
    }

    public void swapItems(ArrayList<PhotoDiaryDo> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
