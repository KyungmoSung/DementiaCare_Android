package com.example.sung.dementiacare.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;

import java.util.List;

/**
 * Created by skm28 on 2017-10-04.
 */

public class PhotoAdapter extends BaseAdapter {
    Context context;
    List<PhotoDiaryModel> data;
    LayoutInflater inf;

    public PhotoAdapter(Context context, List<PhotoDiaryModel> data) {
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
    public PhotoDiaryModel getItem(int position) {
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
                .load(getItem(position).imageUri)
                .into(ImageView);

        return convertView;
    }
}
