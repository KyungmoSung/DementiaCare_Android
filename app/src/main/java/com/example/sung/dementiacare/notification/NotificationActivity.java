package com.example.sung.dementiacare.notification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.notification.CalendarActivity;
import com.example.sung.dementiacare.notification.medicine.NotificationMedicineActivity;
import com.example.sung.dementiacare.notification.information.NotificationTodayInformationActivity;
import com.example.sung.dementiacare.notification.hopemessage.NotificationTodayMessageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_notification);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAlarm));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAlarm));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("정보");
    }

    @OnClick(R.id.card_medicine)
    void medicine(){
        intent = new Intent(getApplicationContext(), NotificationMedicineActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.card_calendar)
    void calendar(){
        intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.card_info)
    void todayInfo(){
        intent = new Intent(getApplicationContext(), NotificationTodayInformationActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.card_message)
    void todayMessage(){
        intent = new Intent(getApplicationContext(), NotificationTodayMessageActivity.class);
        startActivity(intent);

    }

}
