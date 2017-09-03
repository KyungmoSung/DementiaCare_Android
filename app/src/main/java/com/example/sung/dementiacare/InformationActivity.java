package com.example.sung.dementiacare;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class InformationActivity extends AppCompatActivity {
    final int arrayResourceId = R.array.list_info;
    String[] LIST_MENU;

    @BindView(R.id.list_info)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_information);
        Resources res = getResources();
        ButterKnife.bind(this);

        LIST_MENU = res.getStringArray(arrayResourceId);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listview_item_layout, LIST_MENU);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getApplicationContext(), InformationDementiaActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(), InformationCareActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), InformationEnvironmentActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), InformationMediaActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
