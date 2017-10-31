package com.example.sung.dementiacare.information.Environment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;

import java.util.ArrayList;

/**
 * Created by skm28 on 2017-10-04.
 */

public class EnvironmentAdapter extends BaseAdapter {
    Context context;
    ArrayList<EnvironmentDo> data;
    LayoutInflater inf;

    public EnvironmentAdapter(Context context, ArrayList<EnvironmentDo> data) {
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
    public EnvironmentDo getItem(int position) {
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
            convertView = inf.inflate(R.layout.list_item_env, null);
        }
        TextView tv_env = (TextView) convertView.findViewById(R.id.tv_env);
        ImageView iv_env = (ImageView) convertView.findViewById(R.id.iv_env);

        tv_env.setText(getItem(position).getTitle());
        Glide.with(getContext()).load(getItem(position).getImageUri()).into(iv_env);

        return convertView;
    }

    public void swapItems(ArrayList<EnvironmentDo> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
