package com.example.sung.dementiacare.notification.hopemessage;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationTodayMessageActivity extends AppCompatActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.notification_today_layout_textView0)
    TextView tv0;
    @BindView(R.id.notification_today_layout_textView1)
    TextView tv1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_notification_today);
            ButterKnife.bind(this);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.colorAlarm));
            }

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAlarm));
            toolbar_title.setTextColor(Color.WHITE);
            toolbar_title.setText("오늘의 희망 메세지");

            tv0.setText("치매어르신도 존중받아야 할 사람임을 잊지 말아야 합니다. ");
        tv1.setText("인지기능의 손상이 있더라도, 치매어르신은 여전히 자신의 성격과 취향이 있고, 아름다운 추억의 단편들을 지니고 있는 한 사람임을 잊지 말아야 합니다. 따라서 배려한다는 이유로 마냥 아이처럼 대해서는 안되며, 여전히 가족으로부터 존중과 사랑을 받고 있으며, 가정에서 나름의 역할이 있다고 느낄 수 있도록 배려해야 합니다.");


    }
}
