package com.example.sung.dementiacare.notification.medicine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.sung.dementiacare.R;
import com.example.sung.dementiacare.notification.ListViewItem;
import com.example.sung.dementiacare.notification.NotificationChoiceListViewAdapter;
import com.example.sung.dementiacare.notification.alarm.AlarmActivity;
import com.example.sung.dementiacare.notification.alarm.AlarmDao;
import com.example.sung.dementiacare.notification.alarm.AlarmDo;
//import com.example.sung.dementiacare.notification.alarm.AlarmActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class NotificationMedicineItemActivity extends AppCompatActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tvEmpty;

    EditText et0;

    Button bt0;
    Button bt1;
    Button bt2;

    ListView listView0;
    ListView listView1;

    ArrayAdapter adapter;
    ArrayList<AlarmDo> arrayList;

    NotificationChoiceListViewAdapter adapter2;

    private int mode;
    private long index;
    public static final int MODE_CREATE = 0x00000004;
    public static final int MODE_MODIFY = 0x00000005;
    public static final int MODE_VIEW = 0x00000006;

    Intent intent;

    MedicineDao medicineDao;
    MedicineDo medicineDo;

    AlarmDao alarmDao;


    @Override
    protected void onPause() {
        super.onPause();

        if(adapter2 != null)
            adapter2.notifyDataSetChanged();
        if(adapter != null)
            adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAlarm));
        }
        intent = getIntent();

        index = intent.getExtras().getLong("index");
        mode = intent.getExtras().getInt("mode");

        medicineDao = new MedicineDao(getApplicationContext(), null);
        alarmDao = new AlarmDao(getApplicationContext(), null);

        setContentViewMode(mode);

    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayList = alarmDao.getResults();
        if(adapter2 != null)
            adapter2.notifyDataSetChanged();
        if(adapter != null)
            adapter.notifyDataSetChanged();

    }

    @OnClick(R.id.button0)
    void onClickButton0() {

        switch (mode){
            case MODE_CREATE:
                // 확인 Button
                adapter2.notifyDataSetChanged();

                String name = et0.getText().toString();
                MedicineDo medicineDo = new MedicineDo(name, "hello",0);

                long _id = medicineDao.insert(medicineDo);
                medicineDo.setIno(_id);

                SparseBooleanArray checkArr = listView1.getCheckedItemPositions();

                if (checkArr.size() != 0) {
                    for (int i = listView1.getCount() -1; i > -1 ; i--) {
                        if (checkArr.get(i)) {

                            medicineDao.attach(medicineDo.getIno(), adapter2.getListViewItemList().get(i).getAlarmDo().getIno());
                            Log.e("PARK", adapter2.getListViewItemList().get(i).getAlarmDo().getIno()+"onClickButton0: "+medicineDo.getIno() );
                        }
                    }
                }

                finish();
                break;
            case MODE_MODIFY:


                finish();
                break;
            case MODE_VIEW:

                finish();
                break;

        }
    }
    @OnClick(R.id.button1)
    void onClickButton1() {


        switch (mode){
            case MODE_CREATE:

                finish();
                break;
            case MODE_MODIFY:
                // 수정

                mode = MODE_VIEW;
                setContentViewMode(mode);
                break;
            case MODE_VIEW:

                mode = MODE_MODIFY;
                setContentViewMode(mode);
                break;

        }
    }
    void onClickButton0_1() {

        intent = new Intent(getApplicationContext(), AlarmActivity.class);
        intent.putExtra("mode", NotificationMedicineItemActivity.MODE_CREATE);
        startActivity(intent);


    }

    void setContentViewMode(int mode) {
        switch(mode) {
            case MODE_CREATE:

                setContentView(R.layout.medicine_listview_item_add_modify);
                ButterKnife.bind(this);

                tv1 = (TextView)findViewById(R.id.textView0_0);
                tv2 = (TextView)findViewById(R.id.textView0_1);
                tv3 = (TextView)findViewById(R.id.textView1_1);
                bt0 = (Button)findViewById(R.id.button0);
                bt1 = (Button)findViewById(R.id.button1);
                bt2 = (Button)findViewById(R.id.Button0_1);
                et0 = (EditText)findViewById(R.id.editText0_0);

                listView1 = (ListView)findViewById(R.id.list_view1);
                arrayList = alarmDao.getResults();
                adapter2 = new NotificationChoiceListViewAdapter(arrayList);

                tvEmpty = (TextView)findViewById(R.id.tv_empty);
                if (arrayList.size() > 0 ) tvEmpty.setVisibility(View.GONE);

                listView1.setAdapter(adapter2);


                bt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickButton0_1();
                    }
                });

                bt0.setText("완료");
                bt1.setText("취소");
                break;
            case MODE_MODIFY:

                setContentView(R.layout.medicine_listview_item_add_modify);
                ButterKnife.bind(this);

                tv1 = (TextView)findViewById(R.id.textView0_0);
//                tv2 = (TextView)findViewById(R.id.textView0_1);
                tv3 = (TextView)findViewById(R.id.textView1_1);

                et0 = (EditText)findViewById(R.id.editText0_0);

                bt0 = (Button)findViewById(R.id.button0);
                bt1 = (Button)findViewById(R.id.button1);

                listView1 = (ListView)findViewById(R.id.list_view1);
                arrayList = alarmDao.getResults();

                adapter2 = new NotificationChoiceListViewAdapter(arrayList);

                tvEmpty = (TextView)findViewById(R.id.tv_empty);
                if (arrayList.size() > 0 ) tvEmpty.setVisibility(View.GONE);

                listView1.setAdapter(adapter2);


                medicineDo = medicineDao.getResultByIno(index);
                et0.setText(medicineDo.getName());

                bt0.setText("완료");
                bt1.setText("취소");

                break;
            case MODE_VIEW:

                medicineDo = medicineDao.getResultByIno(index);

                setContentView(R.layout.medicine_listview_item);

                ArrayList <AlarmDo> arrayList = alarmDao.getResultsByMedicineId(index);

                adapter2 = new NotificationChoiceListViewAdapter(arrayList);
                adapter2.hideCheckBox = true;
                tvEmpty = (TextView)findViewById(R.id.tv_empty);
                if (arrayList.size() > 0 ) tvEmpty.setVisibility(View.GONE);

//                adapter = new ArrayAdapter(this, R.layout.list_item_info, arrayList);

                tv1 = (TextView)findViewById(R.id.textView0_0);
                tv2 = (TextView)findViewById(R.id.textView0_1);
                tv3 = (TextView)findViewById(R.id.textView1_1);

                bt0 = (Button)findViewById(R.id.button0);
                bt1 = (Button)findViewById(R.id.button1);

                listView0 = (ListView)findViewById(R.id.list_view0);
                listView0.setAdapter(adapter2);
//                listView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        Intent intent = new Intent(getApplicationContext(), NotificationMedicineItemActivity.class);
//                        intent.putExtra("sub_index", position);
//                        startActivity(intent);
//
//                    }
//                });


                ButterKnife.bind(this);

                tv2.setText(medicineDo.getName());

                bt0.setText("닫기");
                bt1.setText("수정");
                break;

            default:



                break;
        }
            tv1.setText("약 이름");
        tv3.setText("알림");

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAlarm));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("약물 알림");
    }
}
