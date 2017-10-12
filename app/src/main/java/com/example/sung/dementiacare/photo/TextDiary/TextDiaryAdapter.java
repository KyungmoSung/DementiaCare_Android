package com.example.sung.dementiacare.photo.TextDiary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.photo.PhotoDiary.PhotoDiaryDo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skm28 on 2017-10-04.
 */

public class TextDiaryAdapter extends BaseAdapter {
    Context context;
    ArrayList<TextDiaryDo> data;
    LayoutInflater inf;

    public TextDiaryAdapter(Context context, ArrayList<TextDiaryDo> data) {
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
    public TextDiaryDo getItem(int position) {
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

        if (convertView == null) {
            convertView = inf.inflate(R.layout.list_item_text_diary, null);
        }
        TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_contents = (TextView) convertView.findViewById(R.id.tv_contents);

        tv_date.setText(getItem(position).getDate());
        tv_title.setText(getItem(position).getTitle());
        tv_contents.setText(getItem(position).getContents());

        return convertView;
    }
}
