package com.example.sung.dementiacare.notification;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notification_today_layout);

        ButterKnife.bind(this);


        tv0.setText("치매어르신도 존중받아야 할 사람임을 잊지 말아야 합니다. ");
        tv1.setText("인지기능의 손상이 있더라도, 치매어르신은 여전히 자신의 성격과 취향이 있고, 아름다운 추억의 단편들을 지니고 있는 한 사람임을 잊지 말아야 합니다. 따라서 배려한다는 이유로 마냥 아이처럼 대해서는 안되며, 여전히 가족으로부터 존중과 사랑을 받고 있으며, 가정에서 나름의 역할이 있다고 느낄 수 있도록 배려해야 합니다.");



    }
}
