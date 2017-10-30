package com.example.sung.dementiacare.notification.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class NotificationMedicineItemActivity extends AppCompatActivity {


    TextView tv1;
    TextView tv2;
    TextView tv3;

    EditText et0;

    Button bt0;
    Button bt1;
    Button bt2;

    ListView listView0;
    ListView listView1;

    String[] menuList = new String[1];
    ArrayAdapter adapter;

    ArrayList<AlarmDo> arrayList;

    NotificationChoiceListViewAdapter adapter2;

    private int mode;
    private int index;
    public static final int MODE_CREATE = 0x00000004;
    public static final int MODE_MODIFY = 0x00000005;
    public static final int MODE_VIEW = 0x00000006;

    Intent intent;

    MedicineDao medicineDao;
    MedicineDo medicineDo;

    AlarmDao alarmDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        medicineDao = new MedicineDao(getApplicationContext(), null);

        alarmDao = new AlarmDao(getApplicationContext(), null);

        intent = getIntent();

        index = intent.getExtras().getInt("index");
        mode = intent.getExtras().getInt("mode");

        setContentViewMode(mode);

    }

    @OnClick(R.id.button0)
    void onClickButton0() {

        switch (mode){
            case MODE_CREATE:
                // 확인 Button
                String name = et0.getText().toString();
                MedicineDo medicineDo = new MedicineDo(name, "hello",0);
                medicineDao.insert(medicineDo);

                for(ListViewItem listViewItem : adapter2.getCheckedItems()) {
                    medicineDao.attach(medicineDo.getIno(), listViewItem.getAlarmDo().getIno());

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

                listView1.setAdapter(adapter2);


                medicineDo = medicineDao.getResultByIno(index);
                et0.setText(medicineDo.getName());

                bt0.setText("완료");
                bt1.setText("취소");

                break;
            case MODE_VIEW:

                medicineDo = medicineDao.getResultByIno(index);

                setContentView(R.layout.medicine_listview_item);

                menuList[0] = "아침";

                alarmDao.getResultsByMedicineId(medicineDo.getIno());



                adapter = new ArrayAdapter(this, R.layout.list_item_info, menuList);

                tv1 = (TextView)findViewById(R.id.textView0_0);
                tv2 = (TextView)findViewById(R.id.textView0_1);
                tv3 = (TextView)findViewById(R.id.textView1_1);

                bt0 = (Button)findViewById(R.id.button0);
                bt1 = (Button)findViewById(R.id.button1);

                listView0 = (ListView)findViewById(R.id.list_view0);
                listView0.setAdapter(adapter);
                listView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getApplicationContext(), NotificationMedicineItemActivity.class);
                        intent.putExtra("sub_index", position);
                        startActivity(intent);

                    }
                });


                ButterKnife.bind(this);



                tv2.setText(medicineDo.getName());

                bt0.setText("확인");
                bt1.setText("수정");
                break;

            default:



                break;
        }
        tv1.setText("약 이름");
        tv3.setText("알림");

    }
}
