package com.example.sung.dementiacare.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import java.lang.reflect.Field;

/**
 * Created by skm28 on 2017-10-04.
 */

public class SupportListAdapter extends BaseAdapter {
    String[][] data;
    Context ctx;

    public SupportListAdapter(String[][] data, Context ctx) {
        super();
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View v = convertview;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(ctx);
            v = vi.inflate(R.layout.list_item_support, null);
        }

        TextView title = (TextView) v.findViewById(R.id.site_title);
        ImageView logo = (ImageView) v.findViewById(R.id.site_logo);

        String[] site = data[position];

        title.setText(site[0]);
        logo.setImageResource(getResId(site[2],R.drawable.class));

        return v;
    }

    public static int getResId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }
}
