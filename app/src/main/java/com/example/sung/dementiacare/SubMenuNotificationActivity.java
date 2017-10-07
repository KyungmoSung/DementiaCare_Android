package com.example.sung.dementiacare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sung.dementiacare.notification.CalendarActivity;
import com.example.sung.dementiacare.notification.medicine.NotificationMedicineActivity;
import com.example.sung.dementiacare.notification.NotificationTodayInformationActivity;
import com.example.sung.dementiacare.notification.NotificationTodayMessageActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class SubMenuNotificationActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_support);

        ButterKnife.bind(this);


        TextView tv1 = (TextView)findViewById(R.id.textView2);
        TextView tv2 = (TextView)findViewById(R.id.textView3);
        TextView tv3 = (TextView)findViewById(R.id.textView4);
        TextView tv4 = (TextView)findViewById(R.id.textView5);

        tv1.setText("약물");
        tv2.setText("일정");
        tv3.setText("오늘의 정보");
        tv4.setText("오늘의 희망 메세지");

    }

    @OnClick(R.id.textView2)
    void onClickLayer1(){

        intent = new Intent(getApplicationContext(), NotificationMedicineActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.textView3)
    void onClickLayer2(){

        intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.textView4)
    void onClickLayer3(){

        intent = new Intent(getApplicationContext(), NotificationTodayInformationActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.textView5)
    void onClickLayer4(){

        intent = new Intent(getApplicationContext(), NotificationTodayMessageActivity.class);
        startActivity(intent);

    }

}
