package com.example.sung.dementiacare.notification.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sung.dementiacare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class NotificationMedicineActivity extends AppCompatActivity {


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

        setContentView(R.layout.information_medicine_item_list_layout);
        ButterKnife.bind(this);


        medicineDao = new MedicineDao(getApplicationContext(), null);

        results = medicineDao.getResults();

        adapter = new ArrayAdapter(this, R.layout.listview_item_layout, results);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                intent = new Intent(getApplicationContext(), NotificationMedicineItemActivity.class);
                intent.putExtra("mode",  NotificationMedicineItemActivity.MODE_VIEW);
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
