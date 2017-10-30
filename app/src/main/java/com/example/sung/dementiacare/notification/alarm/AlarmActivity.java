package com.example.sung.dementiacare.notification.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sung.dementiacare.MainActivity;
import com.example.sung.dementiacare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    @BindView(R.id.button0_0)
    Button button0_0;
    @BindView(R.id.button0_1)
    Button button0_1;
    @BindView(R.id.textView0_0)
    TextView textView0_0;
    @BindView(R.id.textView1_0)
    TextView textView0_1;
    @BindView(R.id.editText0_0)
    EditText editText0_0;
    @BindView(R.id.timePicker0_0)
    TimePicker timePicker0_0;
    AlarmDao alarmDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_alarm_add_modify);

        ButterKnife.bind(this);

        alarmDao = new AlarmDao(getApplicationContext(), null);

        button0_0.setText("확인");
        button0_1.setText("취소");
        textView0_0.setText("알람명");
        textView0_1.setText("반복");

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        Intent receiverIntent = new Intent(AlarmActivity.this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, receiverIntent, 0);

        long period = 1000 * 5;
        long after = 1000 * 5;
        long t = SystemClock.elapsedRealtime();

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, t + after, period, pendingIntent);

    }

    @OnClick(R.id.button0_0)
    void onClickButton0_0() {

        String name = editText0_0.getText().toString();

        AlarmDo alarmDo = new AlarmDo(name, timePicker0_0.getCurrentHour(),timePicker0_0.getCurrentMinute(), 0);
        alarmDao.insert(alarmDo);

        finish();
    }
    @OnClick(R.id.button0_1)
    void onClickButton0_1() {


        finish();
    }
}
