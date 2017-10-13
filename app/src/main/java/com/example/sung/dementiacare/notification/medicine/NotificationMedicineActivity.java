package com.example.sung.dementiacare.notification.medicine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sung.dementiacare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class NotificationMedicineActivity extends AppCompatActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.list_info_title)
    ListView listView;
    @BindView(R.id.fab1)

    FloatingActionButton fab;
    ArrayAdapter adapter;
    Intent intent;
    MedicineDao medicineDao;

    ArrayList<MedicineDo> results;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification_medicine);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAlarm));
        }

        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAlarm));
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("정보");

        medicineDao = new MedicineDao(getApplicationContext(), null);

        results = medicineDao.getResults();

        adapter = new ArrayAdapter(this, R.layout.list_item_info, results);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent = new Intent(getApplicationContext(), NotificationMedicineItemActivity.class);
                intent.putExtra("mode", NotificationMedicineItemActivity.MODE_VIEW);
                intent.putExtra("index", results.get(position).getIno());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        results.clear();
        results.addAll(medicineDao.getResults());
        adapter.notifyDataSetChanged();
        listView.invalidate();

    }


    @OnClick(R.id.fab1)
    void onClickFab() {

        intent = new Intent(getApplicationContext(), NotificationMedicineItemActivity.class);
        intent.putExtra("mode", NotificationMedicineItemActivity.MODE_CREATE);
        startActivity(intent);


    }
}
