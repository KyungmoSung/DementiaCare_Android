package com.example.sung.dementiacare;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.sung.dementiacare.common.EventDialog;
import com.example.sung.dementiacare.information.InformationActivity;
import com.example.sung.dementiacare.information.Video.VideoListActivity;
import com.example.sung.dementiacare.notification.NotificationActivity;
import com.example.sung.dementiacare.notification.TodayDialog;
import com.example.sung.dementiacare.photo.DiaryMenuActivity;
import com.example.sung.dementiacare.support.SupportActivity;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.statusBar));
        }
        getPreferences();
    }

    void todayMessage() {

        int INFORMATION_MEDIA_RESOURCE_ID = R.array.arrays_video;
        String[][] mediaArray = getArrayFromResource(getApplicationContext(),INFORMATION_MEDIA_RESOURCE_ID);

        Random generator = new Random();
        int randomIndex = generator.nextInt(mediaArray.length);

        final String title = mediaArray[randomIndex][0];
        final String url = mediaArray[randomIndex][1];

        final EventDialog dialog = new EventDialog(this);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dia) {
                dialog.setMessage("오늘의 동영상", title, url);
            }
        });

        dialog.show();
    }

    // 값 불러오기
    private void getPreferences(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String eventCheckUpDate = pref.getString("eventCheckUpDate", null);

        if (eventCheckUpDate != null){
            try{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Calendar c = Calendar.getInstance();
                String today = formatter.format(c.getTime());

                Date date1 = formatter.parse(eventCheckUpDate);
                Date date2 = formatter.parse(today);

                Log.e("EventDialog checkUpDate",eventCheckUpDate);
                Log.e("EventDialog today",today);

                if (!date1.equals(date2)) {
                    todayMessage();
                    Log.e("EventDialog","eventCheckUpDate != today");
                } else {
                    Log.e("EventDialog","eventCheckUpDate == today");
                }

            }catch (ParseException e1){
                e1.printStackTrace();
            }
        } else {
            todayMessage();
        }
    }

    private void setupWindowAnimations() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Fade fade = new Fade();
            fade.setDuration(1000);
            getWindow().setEnterTransition(fade);

            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setReturnTransition(slide);
        }
    }

    public static String[][] getArrayFromResource(Context c, int resourceId) {
        Resources res = c.getResources();
        TypedArray ta = res.obtainTypedArray(resourceId);

        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                array[i] = res.getStringArray(id);
                Log.e("array", array[i][0] + ", " + array[i][1]);
            } else {
                Log.e("getStringArray", "Not found");
            }
        }
        ta.recycle();
        return array;
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @OnClick(R.id.layout_information)
    void openInformationActivity() {
        startActivity(new Intent(this, InformationActivity.class));
    }

    @OnClick(R.id.layout_support)
    void openSupportActivity() {
        startActivity(new Intent(this, SupportActivity.class));
    }

    @OnClick(R.id.layout_notification)
    void openNotificationActivity() {
        startActivity(new Intent(this, NotificationActivity.class));
    }

    @OnClick(R.id.layout_picture)
    void openPictureActivity() {
        startActivity(new Intent(this, DiaryMenuActivity.class));
    }
}
