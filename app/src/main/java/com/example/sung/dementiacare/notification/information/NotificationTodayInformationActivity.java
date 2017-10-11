package com.example.sung.dementiacare.notification.information;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationTodayInformationActivity extends AppCompatActivity {

    @BindView(R.id.notification_today_layout_textView0)
    TextView tv0;

    @BindView(R.id.notification_today_layout_textView1)
    TextView tv1;

    InformationDao informationDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification_today_layout);

        ButterKnife.bind(this);

        informationDao = new InformationDao(getApplicationContext(), null);

        InformationDo informationDo = informationDao.resultByRandom();

        tv0.setText(informationDo.getTitle());
        tv1.setText(informationDo.getText());

    }
}
