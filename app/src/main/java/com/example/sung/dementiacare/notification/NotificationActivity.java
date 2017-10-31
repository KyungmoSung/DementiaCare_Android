package com.example.sung.dementiacare.notification;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.notification.CalendarActivity;
import com.example.sung.dementiacare.notification.medicine.NotificationMedicineActivity;
import com.example.sung.dementiacare.notification.information.NotificationTodayInformationActivity;
import com.example.sung.dementiacare.notification.hopemessage.NotificationTodayMessageActivity;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sung.dementiacare.MainActivity.getArrayFromResource;
import static com.example.sung.dementiacare.MainActivity.getResId;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class NotificationActivity extends AppCompatActivity {

    final int NOTIFICATION_RESOURCE_ID = R.array.list_noti_menu;
    String[][] menuList;
    Intent intent;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.iv_menu_1)
    ImageView iv_menu_1;
    @BindView(R.id.iv_menu_2)
    ImageView iv_menu_2;
    @BindView(R.id.iv_menu_3)
    ImageView iv_menu_3;
    @BindView(R.id.iv_menu_4)
    ImageView iv_menu_4;
    @BindView(R.id.tv_menu_1)
    TextView tv_menu_1;
    @BindView(R.id.tv_menu_2)
    TextView tv_menu_2;
    @BindView(R.id.tv_menu_3)
    TextView tv_menu_3;
    @BindView(R.id.tv_menu_4)
    TextView tv_menu_4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAlarm));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAlarm));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("알림");

        menuList = getArrayFromResource(this, NOTIFICATION_RESOURCE_ID);

        ImageView[] imageViews = {iv_menu_1, iv_menu_2, iv_menu_3, iv_menu_4};
        TextView[] textViews = {tv_menu_1, tv_menu_2, tv_menu_3, tv_menu_4};

        for (int i = 0; i < menuList.length; i++) {
            textViews[i].setText(menuList[i][0]);
            Glide.with(this).load(getResId(menuList[i][1], R.drawable.class)).into(imageViews[i]);
        }
    }

    @OnClick(R.id.card_menu_1)
    void medicine() {
        intent = new Intent(getApplicationContext(), NotificationMedicineActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.card_menu_2)
    void calendar() {
        intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.card_menu_3)
    void todayInfo() {
        final TodayDialog dialog = new TodayDialog(this);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dia) {
                dialog.setMessage("오늘의 정보","인지기능의 손상이 있더라도,\n 치매어르신은 여전히 자신의 성격과 취향이 있고,\n 아름다운 추억의 단편들을 지니고 있는 한 사람임을 잊지 말아야 합니다.\n 따라서 배려한다는 이유로 마냥 아이처럼 대해서는 안되며,\n 여전히 가족으로부터 존중과 사랑을 받고 있으며,\n 가정에서 나름의 역할이 있다고 느낄 수 있도록 배려해야 합니다.");
            }
        });

        dialog.show();

//        intent = new Intent(getApplicationContext(), NotificationTodayInformationActivity.class);
//        startActivity(intent);

    }

    @OnClick(R.id.card_menu_4)
    void todayMessage() {

        final TodayDialog dialog = new TodayDialog(this);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dia) {
                dialog.setMessage("오늘의 희망메세지", "자신이 될 수 있는 존재가 되길 희망하는 것이 삶의 목적이다.");
            }
        });

        dialog.show();

//        intent = new Intent(getApplicationContext(), NotificationTodayMessageActivity.class);
//        startActivity(intent);

    }

}
