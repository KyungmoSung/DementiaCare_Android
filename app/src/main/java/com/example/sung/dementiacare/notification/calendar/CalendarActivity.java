package com.example.sung.dementiacare.notification.calendar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.photo.TextDiary.TextDiaryEditActivity;
import com.ldf.calendar.Utils;
import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    TextView textViewYearDisplay;
    TextView textViewMonthDisplay;
    CoordinatorLayout content;
    MonthPager monthPager;
    RecyclerView rvToDoList;

    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private CalendarViewAdapter calendarViewAdapter;
    private CalendarAdapter calendarAdapter;
    private OnSelectDateListener onSelectDateListener;
    private int mCurrentPage = MonthPager.CURRENT_DAY_INDEX;
    private Context context;
    private boolean initiated = false;
    private CalendarDate currentDate;
    private CalendarDate selectDate;

    private ArrayList<CalendarDo> calendarDo  = new ArrayList<>();
    private CalendarDao calendarDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_calendar);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAlarm));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAlarm));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("일정");

        calendarDao = new CalendarDao(getApplicationContext(), null);
        calendarDo = calendarDao.getResults();

        context = this;
        calendarAdapter = new CalendarAdapter(this);
        content = (CoordinatorLayout) findViewById(R.id.content);
        monthPager = (MonthPager) findViewById(R.id.calendar_view);
        monthPager.setViewheight(Utils.dpi2px(context, 330));
        textViewYearDisplay = (TextView) findViewById(R.id.show_year_view);
        textViewMonthDisplay = (TextView) findViewById(R.id.show_month_view);
        rvToDoList = (RecyclerView) findViewById(R.id.list);
        rvToDoList.setHasFixedSize(true);
        rvToDoList.setLayoutManager(new LinearLayoutManager(this));
        rvToDoList.setAdapter(calendarAdapter);
        calendarAdapter.setData(calendarDo);
        initCurrentDate();
        initCalendarView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calendarDo = calendarDao.getResults();
        calendarAdapter.swapItems(calendarDo);
        calendarViewAdapter.notifyDataSetChanged();
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !initiated) {
            refreshMonthPager();
            initiated = true;
        }
    }

    private void initCurrentDate() {
        currentDate = new CalendarDate();
        textViewYearDisplay.setText(currentDate.getYear() + "");
        textViewMonthDisplay.setText(currentDate.getMonth() + "");
    }

    private void initCalendarView() {
        initListener();
        EventDayView customDayView = new EventDayView(context, R.layout.calendar_event_day);
        calendarViewAdapter = new CalendarViewAdapter(
                context,
                onSelectDateListener,
                CalendarAttr.CalendayType.MONTH,
                customDayView);
        initMarkData();
        initMonthPager();
    }


    private void initMarkData() {
        HashMap<String, String> markData = new HashMap<>();
        for(CalendarDo data:calendarDo) {
            markData.put(data.getDate(), "1");
        }
        calendarViewAdapter.setMarkData(markData);
    }

    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                selectDate = date;
                ArrayList<CalendarDo> selectCalendarDo = new ArrayList<>();
                refreshClickDate(date);

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Calendar cal  = java.util.Calendar.getInstance();
                for(CalendarDo data : calendarDo) {
                    try {
                        cal.setTime(df.parse(data.getDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if((date.year == cal.get(java.util.Calendar.YEAR)) && (date.month == cal.get(java.util.Calendar.MONTH) + 1) && (date.day == cal.get(java.util.Calendar.DATE))){
                        selectCalendarDo.add(data);
                    }
                }
                calendarAdapter.setData(selectCalendarDo);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                monthPager.selectOtherMonth(offset);
            }
        };
    }

    private void refreshClickDate(CalendarDate date) {
        currentDate = date;
        textViewYearDisplay.setText(date.getYear() + "년");
        textViewMonthDisplay.setText(date.getMonth() + "");
    }


    private void initMonthPager() {
        monthPager.setAdapter(calendarViewAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                currentCalendars = calendarViewAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) instanceof Calendar) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    textViewYearDisplay.setText(date.getYear() + "년");
                    textViewMonthDisplay.setText(date.getMonth() + "");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void refreshMonthPager() {
        CalendarDate today = new CalendarDate();
        calendarViewAdapter.notifyDataChanged(today);
        textViewYearDisplay.setText(today.getYear() + "년");
        textViewMonthDisplay.setText(today.getMonth() + "");
    }

    @OnClick(R.id.fab)
    public void add() {
        final CalendarEditDialog dialog = new CalendarEditDialog(context);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dia) {
                CalendarDate date;
                if(selectDate != null){
                    date = selectDate;
                } else{
                    date = currentDate;
                }
                CalendarDo data = new CalendarDo(date.toString());
                dialog.isEditMode = false;
                dialog.setMessage(data.getDate());
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                refreshEvent();

            }
        });
        dialog.show();

    }

    public void refreshEvent(){
        calendarDo = calendarDao.getResults();
        calendarAdapter.swapItems(calendarDo);
        initMarkData();
    }
}
