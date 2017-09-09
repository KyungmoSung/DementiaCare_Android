package com.example.sung.dementiacare;

import android.content.Intent;
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

public class InformationDementiaActivity extends AppCompatActivity {
    final int MENU_INDEX = 0;
    final int[] ARRAY_RESOURCE_ID = {R.array.list_info_title_dementia, R.array.sub_list_info_title_dementia_1, R.array.sub_list_info_title_dementia_2, R.array.sub_list_info_title_dementia_3, R.array.sub_list_info_title_dementia_4};

    int mainIndex;
    static String[] menuList;

    @BindView(R.id.list_info_title)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu_information);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("main_index")) {
            mainIndex = intent.getIntExtra("main_index", 0);
            menuList = getResources().getStringArray(ARRAY_RESOURCE_ID[mainIndex + 1]);
        } else {
            menuList = getResources().getStringArray(ARRAY_RESOURCE_ID[0]);
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.listview_item_layout, menuList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                if (intent.hasExtra("main_index")) {
                    intent = new Intent(getApplicationContext(), PdfViewerActivity.class);
                    intent.putExtra("menu_index", MENU_INDEX);
                    intent.putExtra("main_index", mainIndex);
                    intent.putExtra("sub_index", position);
                    startActivity(intent);
                } else {
                    intent = new Intent(getApplicationContext(), InformationDementiaActivity.class);
                    intent.putExtra("main_index", position);
                    startActivity(intent);
                }
            }
        });
    }
}
