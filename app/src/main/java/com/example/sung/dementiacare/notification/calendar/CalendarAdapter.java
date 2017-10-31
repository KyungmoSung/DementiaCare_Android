package com.example.sung.dementiacare.notification.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by ldf on 17/6/14.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private ArrayList<CalendarDo> calendar;
    private boolean isEmpty = true;
    private CalendarDao calendarDao;

    public CalendarAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        calendarDao = new CalendarDao(context, null);
    }

    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.calendar_event_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (isEmpty) {
            holder.tvEmpty.setVisibility(View.VISIBLE);
            holder.cvEvent.setVisibility(View.GONE);
        } else {
            holder.tvEmpty.setVisibility(View.GONE);
            holder.cvEvent.setVisibility(View.VISIBLE);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            try {
                cal.setTime(df.parse(calendar.get(position).getDate()));
                int year = cal.get(java.util.Calendar.YEAR);
                int month = cal.get(java.util.Calendar.MONTH) + 1;
                int date = cal.get(java.util.Calendar.DATE);

                holder.tvDate.setText(year + "년 " + month + "월 " + date + "일");

            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.tvTime.setText(calendar.get(position).getsTime() + " ~ " + calendar.get(position).geteTime());
            holder.tvTitle.setText(calendar.get(position).getTitle());
            holder.tvContents.setText(calendar.get(position).getContents());

            final PopupMenu mPopupMenu = new PopupMenu(context, holder.btnMore);
            MenuInflater menuInflater = mPopupMenu.getMenuInflater();
            menuInflater.inflate(R.menu.menu_main, mPopupMenu.getMenu());
            mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.action_modify:
                            final CalendarEditDialog dialog = new CalendarEditDialog(context);

                            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(DialogInterface dia) {
                                    CalendarDo data = calendar.get(position);
                                    dialog.isEditMode = true;
                                    dialog.calendarDo = data;
                                    dialog.setMessage(data.getDate(), data.getsTime(), data.geteTime(), data.getTitle(), data.getContents());
                                }
                            });
                            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    notifyDataSetChanged();
                                }
                            });

                            dialog.show();
                            break;
                        case R.id.action_delete:
                            Log.e("delete","--------------");
                            CalendarDo data = calendar.get(position);
                            calendarDao.delete(data);
                            notifyDataSetChanged();
                            break;
                    }
                    return false;
                }
            });
            holder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mPopupMenu.show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (calendar == null || calendar.size() == 0) {
            isEmpty = true;
            return 1;
        } else {
            isEmpty = false;
            return calendar.size();
        }
    }

    public void setData(ArrayList<CalendarDo> data) {
        this.calendar = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvEvent;
        TextView tvEmpty;
        TextView tvDate;
        TextView tvTime;
        TextView tvTitle;
        TextView tvContents;
        ImageButton btnMore;

        ViewHolder(View view) {
            super(view);
            cvEvent = (CardView) view.findViewById(R.id.cv_event);
            tvEmpty = (TextView) view.findViewById(R.id.tv_empty);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvContents = (TextView) view.findViewById(R.id.tv_contents);
            btnMore = (ImageButton) view.findViewById(R.id.btn_more);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }

    public void swapItems(ArrayList<CalendarDo> data) {
        this.calendar = data;
        notifyDataSetChanged();
    }
}
