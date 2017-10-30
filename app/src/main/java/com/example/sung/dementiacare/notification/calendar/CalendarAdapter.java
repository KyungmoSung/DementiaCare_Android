package com.example.sung.dementiacare.notification.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

/**
 * Created by ldf on 17/6/14.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private String[] titles = {"일정 1","일정 2","일정 3"};

    public CalendarAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.calendar_event_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }
}
